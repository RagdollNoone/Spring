package com.strategy;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

public interface SendMsgStrategy {
    void sendMsg(DefaultMQProducer producer) throws InterruptedException;
}
