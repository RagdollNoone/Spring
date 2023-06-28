package com.strategy;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

// 单向发送
public class OnewaySendMsg implements SendMsgStrategy {
    @Override
    public void sendMsg(DefaultMQProducer producer) {
        int messageCount = 20;
        try {
            for (int i = 0; i < messageCount; i++) {
                Message message = new Message("MyTopic", "OnewayTag", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.sendOneway(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
