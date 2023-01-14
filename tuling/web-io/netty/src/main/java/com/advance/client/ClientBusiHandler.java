package com.advance.client;

import com.advance.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientBusiHandler extends SimpleChannelInboundHandler<MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        log.info("业务应答消息："+msg.toString());
    }
}
