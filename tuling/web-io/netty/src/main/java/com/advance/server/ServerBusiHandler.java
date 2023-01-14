package com.advance.server;

import com.advance.server.asyncpro.AsyncBusiProcess;
import com.advance.server.asyncpro.ITaskProcessor;
import com.advance.utils.EncryptUtils;
import com.advance.vo.MessageType;
import com.advance.vo.MsgHeader;
import com.advance.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerBusiHandler extends SimpleChannelInboundHandler<MyMessage> {
    private ITaskProcessor taskProcessor;

    public ServerBusiHandler(ITaskProcessor taskProcessor) {
        super();
        this.taskProcessor = taskProcessor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg)
            throws Exception {
        /*检查MD5*/
        String headMd5 = msg.getMyHeader().getMd5();
        String calcMd5 = EncryptUtils.encryptObj(msg.getBody());
        if(!headMd5.equals(calcMd5)){
            log.error("报文md5检查不通过："+headMd5+" vs "+calcMd5+"，关闭连接");
            ctx.writeAndFlush(buildBusiResp("报文md5检查不通过，关闭连接"));
            ctx.close();
        }
        log.info(msg.toString());
        if(msg.getMyHeader().getType() == MessageType.ONE_WAY.value()){
            log.debug("ONE_WAY类型消息，异步处理");
            AsyncBusiProcess.submitTask(taskProcessor.execAsyncTask(msg));
        }else{
            log.debug("TWO_WAY类型消息，应答");
            ctx.writeAndFlush(buildBusiResp("OK"));
        }
    }

    private MyMessage buildBusiResp(String result) {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.SERVICE_RESP.value());
        message.setMyHeader(msgHeader);
        message.setBody(result);
        return message;
    }
}
