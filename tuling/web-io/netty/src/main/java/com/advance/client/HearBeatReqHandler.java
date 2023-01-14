package com.advance.client;

import com.advance.vo.MessageType;
import com.advance.vo.MsgHeader;
import com.advance.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HearBeatReqHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt == IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT){
            MyMessage heartBeat = buildHeatBeat();
            log.debug("写空闲，发出心跳报文维持连接： "+ heartBeat);
            ctx.writeAndFlush(heartBeat);
        }
        super.userEventTriggered(ctx, evt);
    }

    private MyMessage buildHeatBeat() {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.HEARTBEAT_REQ.value());
        message.setMyHeader(msgHeader);
        return message;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        /*是不是心跳的应答*/
        if(message.getMyHeader() != null && message.getMyHeader().getType()==MessageType.HEARTBEAT_RESP.value()){
            log.debug("收到服务器心跳应答，服务器正常");
            ReferenceCountUtil.release(msg);
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关联ReadTimeoutHandler 长时间没有读到对应客户端数据时 抛出异常
        // 并且在这里捕获后 断开连接 清除半包 等待重连
        if(cause instanceof ReadTimeoutException){
            log.warn("服务器长时间未应答，关闭链路");
            //ctx.close();
        }
        super.exceptionCaught(ctx, cause);
    }
}
