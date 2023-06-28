package com;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.*;

public class TransactionProducer {
    public static final ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("client-transaction-msg-check-thread");
            return thread;
        }
    });

    public static void main(String[] args) throws InterruptedException, MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("transaction_message_producer");
        producer.setNamesrvAddr("192.168.20.129:9876");
        producer.setExecutorService(executorService);

        TransactionListener transactionListener = new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);

        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TransactionMessageTopic", tags[i % tags.length], ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//                msg.putUserProperty("CHECK_IMMUNITY_TIME_IN_SECONDS","10000");
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 生产者程序一直存活
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }

        // 象征性的有个shutdown
        producer.shutdown();
    }
}
