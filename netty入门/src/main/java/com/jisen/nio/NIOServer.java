package com.jisen.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jisen on 2018/1/29.
 */
public class NIOServer {

    /*标识数字*/
    private  int flag = 0;
    /*缓冲区大小*/
    private  int BLOCK = 4096;
    /*接受数据缓冲区*/
    private  ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    private  Selector selector;

    public NIOServer(int port) throws IOException {

        /**
         * Selector是一个多路复用器,提供选择已经就绪的能力,Selector会不断轮询注册在其上面的channel,
         * 如果某个channel上面发生了读写事件,这个channel就处于就绪状态,会被selector轮询出来
         */
        selector = Selector.open();

        /**
         * Channel用于网络读写,就像管道一样
         */
        // 打开服务器套接字通道,用于监听客户端的连接,它是所有客户端连接的父管道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);


        // 检索与此通道关联的服务器套接字,大多数情况检索和绑定会一起写在一行
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 进行服务的绑定,绑定监听端口,
        //serverSocket.bind(new InetSocketAddress(port));


        // 注册到selector，等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server Start----8888:");
    }


    // 监听
    private void listen() throws IOException {
        /**
         * 循环遍历selector,
         */
        while (true) {
            //无论是否有读写时间发生,selector每隔1秒中唤醒一次
            selector.select(1000);

            // 选择一组键，并且相应的通道已经打开
            //selector.select();
            // 返回此选择器的已选择键集。
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handleKey(selectionKey);
            }
        }
    }

    // 处理请求,这么多的if,else表示为事件驱动型,selectionKeys中的三个事件,连接,可读,可写
    private void handleKey(SelectionKey selectionKey) throws IOException {
        // 接受请求
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel clientSocketChannel = null;
        String receiveText;
        String sendText;
        int count=0;
        // 测试此键的通道是否已准备好接受新的套接字连接。
        //处理新接入的请求的消息
        if (selectionKey.isAcceptable()) {
            // 返回为之创建此键的通道。践行了从channel管道读写
            serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

            // 接受到此通道套接字的连接。TCP物理链路正式建立
            // 此方法返回的套接字通道（如果有）将处于阻塞模式。
            clientSocketChannel = serverSocketChannel.accept();
            // 配置为非阻塞
            clientSocketChannel.configureBlocking(false);
            // 注册到selector为读，等待连接
            clientSocketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            // 返回为之创建此键的通道。
            clientSocketChannel = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下次读取
            receivebuffer.clear();
            //读取服务器发送来的数据到缓冲区中
            count = clientSocketChannel.read(receivebuffer);
            if (count > 0) {
                receiveText = new String( receivebuffer.array(),0,count);
                System.out.println("服务器端接受客户端数据--:"+receiveText);
                clientSocketChannel.register(selector, SelectionKey.OP_WRITE);
            }
        } else if (selectionKey.isWritable()) {
            //将缓冲区清空以备下次写入
            sendbuffer.clear();
            // 返回为之创建此键的通道。
            clientSocketChannel = (SocketChannel) selectionKey.channel();
            sendText="message from server--" + flag++;
            //向缓冲区中输入数据
            sendbuffer.put(sendText.getBytes());
            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            sendbuffer.flip();
            //输出到通道
            clientSocketChannel.write(sendbuffer);
            System.out.println("服务器端向客户端发送数据--："+sendText);
            clientSocketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int port = 8888;
        NIOServer server = new NIOServer(port);
        server.listen();
    }
}
