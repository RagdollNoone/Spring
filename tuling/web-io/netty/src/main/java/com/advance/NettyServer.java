package com.advance;

import com.advance.constant.NettyConstant;
import com.advance.server.ServerInit;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {
    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
        EventLoopGroup workerGroup = new NioEventLoopGroup(NettyRuntime.availableProcessors(), new DefaultThreadFactory("nt_worker"));

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ServerInit());

        // 绑定端口，同步等待成功
        b.bind(NettyConstant.SERVER_PORT).sync();
        log.info("Netty server start : "
                + (NettyConstant.SERVER_IP + " : "
                + NettyConstant.SERVER_PORT));
    }
}
