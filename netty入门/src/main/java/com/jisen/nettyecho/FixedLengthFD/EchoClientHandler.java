package com.jisen.nettyecho.FixedLengthFD;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by jisen on 2018/11/1.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    static final String ECHO_REQ = "Hi, xxx. welcome to Netty.$_";
    public EchoClientHandler(){

    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0;i<10;i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is "+ ++counter + "times receive server:["+msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
