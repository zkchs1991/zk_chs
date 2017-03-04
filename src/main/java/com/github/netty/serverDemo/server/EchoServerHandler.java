package com.github.netty.serverDemo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by zk_chs on 16/10/9.
 */
@Sharable /** 表明一个ChannelHandler可以被多个Channel安全地共享 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /** 每次收到消息时被调用 */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        long current = System.currentTimeMillis();
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8) + " now is " + current);
        ctx.write(in);
    }

    /** 用来通知handler上一个channelRead()是被这批消息中的最后一个消息调用 */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    /** 在读操作异常被抛出时被调用 */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); /** 打印异常堆栈信息 */
        ctx.close(); /** 关闭这个Channel */
    }
}