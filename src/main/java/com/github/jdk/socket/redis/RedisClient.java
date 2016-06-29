package com.github.jdk.socket.redis;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zk_chs on 16/6/28.
 */
public class RedisClient {

    private byte[] bytes;
    private int count;
    private String ip;
    private int port;
    private Socket socket;
    private BufferedInputStream input;
    private OutputStream output;

    public RedisClient(String ip, int port){
        this.port = port;
        this.ip = ip;
        bytes = new byte[1024];
        count = 0;
    }

    public RedisClient(String ip, int port, int size){
        this.port = port;
        this.ip = ip;
        bytes = new byte[size];
        count = 0;
    }

    public RedisClient connect () {
        try {
            socket = new Socket(ip, port);
            input = new BufferedInputStream(socket.getInputStream());
            output = socket.getOutputStream();
        } catch (IOException e) {
            throw new RedisException("some exception throws while connect redis");
        }
        return this;
    }

    public void flush () throws IOException {
        if (output != null)
            output.flush();
    }

    public void close () throws IOException {
        if (input != null)
            input.close();
        if (output != null)
            output.close();
        if (socket != null)
            socket.close();
    }

    public void keys (String... args) throws IOException {
        byte[] command = Const.COMMAND.KEYS.name().getBytes();
        int length = args.length + 1;
        int argsLength = args.length;
        this.bytes[count++] = Const.ASTERISK_BYTE;
        this.bytes[count++] = '2';
//        this.bytes[count++] = new Integer(length).byteValue();
        writeCRLF();
        this.bytes[count++] = Const.DOUBLE_BYTE;
//        this.bytes[count++] = new Integer(command.length).byteValue();
        this.bytes[count++] = '4';
        writeCRLF();
        System.arraycopy(command, 0, this.bytes, count, command.length);
        count += command.length;
        writeCRLF();
        for (int i = 0; i < argsLength; i++){
            this.bytes[count++] = Const.DOUBLE_BYTE;
//            this.bytes[count++] = new Integer(args[i].length()).byteValue();
            this.bytes[count++] = 49;
            this.bytes[count++] = 55;
            writeCRLF();
            byte[] argBytes = args[i].getBytes();
            System.arraycopy(argBytes, 0, bytes, count, argBytes.length);
            count += argBytes.length;
            writeCRLF();
        }
        output.write(bytes, 0, count);
        output.flush();
    }

    private void writeCRLF() throws IOException {
        this.bytes[count++] = '\r';
        this.bytes[count++] = '\n';
    }

    public BufferedInputStream getInput() {
        return input;
    }

    public OutputStream getOutput() {
        return output;
    }

    public static void main(String[] args) throws IOException {
        RedisClient client = new RedisClient("10.0.22.24", 6379).connect();
        client.keys("1227898377@qq.com");
        int b;
        StringBuilder sb = new StringBuilder();
        while ((b = client.getInput().read()) != -1){
            sb.append((char) b);
        }
        System.out.println(sb);
        client.close();
    }

    private void addCRLF (List<Byte> bytes){
        byte b1 = 0x0d;
        byte b2 = 0x0a;
        bytes.add(b1);
        bytes.add(b2);
    }

    private void addBytes (List<Byte> bytes, byte[] addition){
        for (int i = 0; i < addition.length; i++){
            bytes.add(addition[i]);
        }
    }

    private void test () throws IOException {
        new RedisClient("10.0.22.24", 6379).connect();

        List<Byte> bytes = new ArrayList<>();
        this.addBytes(bytes, "*2".getBytes());
        this.addCRLF(bytes);
        this.addBytes(bytes, "$4".getBytes());
        this.addCRLF(bytes);
        this.addBytes(bytes, "KEYS".getBytes());
        this.addCRLF(bytes);
        this.addBytes(bytes, "$17".getBytes());
        this.addCRLF(bytes);
        this.addBytes(bytes, "1227898377@qq.com".getBytes());
        this.addCRLF(bytes);
        byte[] request = new byte[38];
        for (int i = 0; i < 38; i++){
            request[i] = bytes.get(i);
        }

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(request);
        byte[] buf = new byte[28];
        InputStream inputStream = socket.getInputStream();
        inputStream.read(buf);
        System.out.print(new String(buf, "UTF-8"));
    }

}
