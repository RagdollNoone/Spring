package com.strategy;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

// 同步发送
public class SynSendMsg implements SendMsgStrategy {
    @Override
    public void sendMsg(DefaultMQProducer producer) throws InterruptedException {
        int messageCount = 2;
        for (int i = 0; i < messageCount; i++) {
            try {
                // 创建消息
                Message msg = new Message("MyTopic" /* Topic */,
                                           "SynTag" /* Tag */,
                                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );

                SendResult sendResult = producer.send(msg); // 同步发送消息
                System.out.printf("%s%n", sendResult); // 打印日志
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
    }
}
