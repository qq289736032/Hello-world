package com.jisen.nettyserializable;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by jisen on 2018/11/2.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionuID = 1L;
    private String userName;
    private int userID;
    public UserInfo buildUserName(String userName){
        this.userName = userName;
        return this;
    }
    public  UserInfo buildUserID(int userID){
        this.userID = userID;
        return this;
    }
    public final String getUserName(){
        return userName;
    }
    public final void setUserName(String userName){
        this.userName=userName;
    }
    public final int getUserID(){
        return userID;
    }
    public final void setUserID(int userID){
        this.userID=userID;
    }

    public byte[] codeC(ByteBuffer buffer){
        buffer.clear();
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userID);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;

    }
}
