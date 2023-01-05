package com.shareHandler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.atomic.AtomicLong;

@ChannelHandler.Sharable
public class MessageCountHandler extends ChannelDuplexHandler {
    private AtomicLong inCount = new AtomicLong(0);
    private AtomicLong outCount = new AtomicLong(0);
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到报文总数：" + inCount.incrementAndGet());
        super.channelRead(ctx, msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println("发出报文总数：" + outCount.incrementAndGet());
        super.flush(ctx);
    }
}
