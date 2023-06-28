package com;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

public class OrderMessageProducer {
    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("order_message_producer");
            producer.setNamesrvAddr("192.168.20.129:9876");
            producer.start();

            // 一共10个订单
            // 一个订单有6个步骤
            // 要求每个订单执行是有序的
            // 不同订单间的步骤是无序的
            for (int i = 0; i < 10; i++) {
                int orderId = i;

                for (int j = 0; j <= 5; j++) {
                    Message msg = new Message("OrderMessageTopic", "order_" + orderId, ("order_" + orderId + " step " + j).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                        // 计算消息发送到那个队列上去
                        // mqs是broker下所有的messageQueue
                        @Override
                        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                            Integer id = (Integer) arg;
                            int index = id % mqs.size();
                            return mqs.get(index);
                        }
                    }, orderId); // orderId <---> arg

                    System.out.printf("%s%n", sendResult);
                }
            }

            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
