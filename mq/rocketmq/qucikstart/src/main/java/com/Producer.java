package com;

import com.strategy.AsyncSendMsg;
import com.strategy.OnewaySendMsg;
import com.strategy.SendMsgStrategy;
import com.strategy.SynSendMsg;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;


/*
 * 最基本的rocketmq使用示例
 * 发送端三种发送方式 同步 异步 oneway 的示例
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("default_producer");
        producer.setNamesrvAddr("192.168.20.129:9876");
//        producer.setClientIP("192.168.20.1");
        producer.start();

//        SendMsgStrategy sendMsgStrategy = new SynSendMsg();
//        SendMsgStrategy sendMsgStrategy = new AsyncSendMsg();
        SendMsgStrategy sendMsgStrategy = new OnewaySendMsg();
        sendMsgStrategy.sendMsg(producer);

        producer.shutdown();
    }
}
