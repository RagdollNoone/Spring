package com.strategy;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;

// 异步发送
public class AsyncSendMsg implements SendMsgStrategy {
    @Override
    public void sendMsg(DefaultMQProducer producer) throws InterruptedException {
//        producer.setRetryTimesWhenSendAsyncFailed(3);

        int messageCount = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        try {

            for (int i = 0; i < messageCount; i++) {
                final int index = i;
                Message message = new Message("MyTopic", "AsyncTag", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d Exception %s %n", index, throwable);
                        throwable.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }
        
    }
}
