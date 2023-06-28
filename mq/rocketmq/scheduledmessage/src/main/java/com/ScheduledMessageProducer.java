package com;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class ScheduledMessageProducer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("scheduled_message_producer");
        producer.setNamesrvAddr("192.168.20.129:9876");
        producer.start();

        int messageCount = 2;
        for (int i = 0; i < messageCount; i++) {
            try {
                // 创建消息
                Message msg = new Message("ScheduledMessageTopic" /* Topic */,
                                           "SynTag" /* Tag */,
                                               ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );

                // 延时等级 <---> 延时时间
                // messageDelayLevel = 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                msg.setDelayTimeLevel(3);

                SendResult sendResult = producer.send(msg); // 同步发送消息
                System.out.printf("%s%n", sendResult); // 打印日志
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        producer.shutdown();
    }
}
