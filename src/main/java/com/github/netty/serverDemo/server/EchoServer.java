package com.github.netty.serverDemo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by zk_chs on 16/10/9.
 * 几个快捷键: option + J => 快速查看document, command + Y => 快速查看类的内容定义
 */
public class EchoServer {

    private final int port;

    public EchoServer (int port){
        this.port = port;
    }

    public void start () throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup(); /** 创建EventLoopGroup */
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class) /** 指定一个NIO传输Channel */
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel> (){ /** 在Channel的ChannelPipeline中加入EchoServerHandler */
                        @Override /** 当一个新的连接被接收时,一个新的子Channel会被创建,然后会进行初始化 */
                        protected void initChannel (SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f = b.bind().sync(); /** 异步地绑定服务器,sync()一直等到绑定完成 */
            f.channel().closeFuture().sync(); /** 获取这个Channel的CloseFuture,阻塞当前线程直到关闭操作完成 */
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(39999).start();
    }

}
