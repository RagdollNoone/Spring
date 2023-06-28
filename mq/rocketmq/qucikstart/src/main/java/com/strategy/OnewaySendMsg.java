package com.strategy;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

// 单向发送
public class OnewaySendMsg implements SendMsgStrategy {
    @Override
    public void sendMsg(DefaultMQProducer producer) {

    }
}
