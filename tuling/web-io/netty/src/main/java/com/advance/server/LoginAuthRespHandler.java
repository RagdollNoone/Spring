package com.advance.server;

import com.advance.vo.MessageType;
import com.advance.vo.MsgHeader;
import com.advance.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        
        /*是不是握手认证请求*/
        if(message.getMyHeader() != null && message.getMyHeader().getType() == MessageType.LOGIN_REQ.value()){
            log.info("收到客户端认证请求 : " + message);
            String nodeIndex = ctx.channel().remoteAddress().toString();
            log.info("nodeIndex is: " + nodeIndex);
            MyMessage loginResp = null;
            boolean chkAuthPass = false;
            /* 重复登陆，拒绝，这里用客户端的地址代替了实际的用户信息*/
            if (SecurityCenter.isDupLog(nodeIndex)) {
                loginResp = buildResponse((byte) -1);
                log.warn("拒绝重复登录，应答消息 : " + loginResp);
                ctx.writeAndFlush(loginResp);
                ctx.close();
            } else {
                /*检查用户是否在白名单中，在则允许登录，并写入缓存*/
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                if(SecurityCenter.isWhiteIP(ip)){
                    SecurityCenter.addLoginUser(nodeIndex);
                    loginResp = buildResponse((byte) 0);
                    log.info("认证通过，应答消息 : " + loginResp);
                    ctx.writeAndFlush(loginResp);
                }else{
                    loginResp = buildResponse((byte) - 1);
                    log.warn("认证失败，应答消息 : " + loginResp);
                    ctx.writeAndFlush(loginResp);
                    ctx.close();
                }
            }
            ReferenceCountUtil.release(msg);
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    private MyMessage buildResponse(byte result) {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.LOGIN_RESP.value());
        message.setMyHeader(msgHeader);
        message.setBody(result);
        return message;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 删除缓存
        SecurityCenter.removeLoginUser(ctx.channel().remoteAddress().toString());
        ctx.close();
    }
}
