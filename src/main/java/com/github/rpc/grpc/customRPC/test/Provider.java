package com.github.rpc.grpc.customRPC.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zk_chs on 16/6/27.
 */
public class Provider {

    /** 经测试,ObjectOutputStream会在每次调用writeUTF之前,现将字符串的字节数传递过去,最开始读取到的字节为可以读到的字节数  如5 18 表示 5*256+18 = 1298 */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);
        Socket socket = server.accept();
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        String utf = input.readLine();
        byte[] bytes = utf.getBytes();
        for (int i = 0; i < bytes.length; i++){
            System.out.println(bytes[i]);
        }
        input.close();
        socket.close();
    }

}
