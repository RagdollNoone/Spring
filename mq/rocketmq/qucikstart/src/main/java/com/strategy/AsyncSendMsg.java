package com.strategy;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

// 异步发送
public class AsyncSendMsg implements SendMsgStrategy {
    @Override
    public void sendMsg(DefaultMQProducer producer) {

    }
}
