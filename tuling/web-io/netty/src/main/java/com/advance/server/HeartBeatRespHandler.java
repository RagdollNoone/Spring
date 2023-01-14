package com.advance.server;

import com.advance.vo.MessageType;
import com.advance.vo.MsgHeader;
import com.advance.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        MyMessage message = (MyMessage) msg;
        /*是不是心跳请求*/
        if(message.getMyHeader()!=null && message.getMyHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
            /*心跳应答报文*/
            MyMessage heartBeatResp = buildHeatBeat();
            log.debug("心跳应答： "+ heartBeatResp);
            ctx.writeAndFlush(heartBeatResp);
            ReferenceCountUtil.release(msg);
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    private MyMessage buildHeatBeat() {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.HEARTBEAT_RESP.value());
        message.setMyHeader(msgHeader);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(cause instanceof ReadTimeoutException){
            log.warn("客户端长时间未通信，可能已经宕机，关闭链路");
            SecurityCenter.removeLoginUser(ctx.channel().remoteAddress().toString());
            ctx.close();
        }
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("客户端已关闭连接");
        super.channelInactive(ctx);
    }
}
