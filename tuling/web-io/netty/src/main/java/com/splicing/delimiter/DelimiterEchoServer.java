package com.splicing.delimiter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import java.net.InetSocketAddress;

public class DelimiterEchoServer {
    public static final String DELIMITER_SYMBOL = "@~";
    public static final int PORT = 9997;

    public static void main(String[] args) throws InterruptedException {
        DelimiterEchoServer delimiterEchoServer = new DelimiterEchoServer();
        System.out.println("服务器即将启动");
        delimiterEchoServer.start();
    }

    public void start() throws InterruptedException {
        final DelimiterServerHandler serverHandler = new DelimiterServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();/*线程组*/

        try {
            ServerBootstrap b = new ServerBootstrap();/*服务端启动必须*/
            b.group(group)/*将线程组传入*/
                    .channel(NioServerSocketChannel.class)/*指定使用NIO进行网络传输*/
                    .localAddress(new InetSocketAddress(PORT))/*指定服务器监听端口*/
                    .childHandler(new ChannelInitializerImp());

            ChannelFuture f = b.bind().sync();/*异步绑定到服务器，sync()会阻塞直到完成*/
            System.out.println("服务器启动完成，等待客户端的连接和数据.....");
            f.channel().closeFuture().sync();/*阻塞直到服务器的channel关闭*/
        } finally {
            group.shutdownGracefully().sync();/*优雅关闭线程组*/
        }
    }

    private static class ChannelInitializerImp extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER_SYMBOL.getBytes());
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter)); // 以自定义分割符解决粘包问题
            ch.pipeline().addLast(new DelimiterServerHandler());
        }
    }
}
