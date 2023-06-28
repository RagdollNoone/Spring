package com;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class TransactionListenerImpl implements TransactionListener {
    // 执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String tags = msg.getTags();

        if (StringUtils.contains(tags, "TagA")) {
            return LocalTransactionState.COMMIT_MESSAGE; // 立刻发送
        } else if (StringUtils.contains(tags, "TagB")) {
            return LocalTransactionState.ROLLBACK_MESSAGE; // 立刻丢弃
        } else {
            return LocalTransactionState.UNKNOW; // 等待回查
        }
    }

    // 本地事务回查
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String tags = msg.getTags();

        if (StringUtils.contains(tags, "TagC")) {
            return LocalTransactionState.COMMIT_MESSAGE; // 回查后发送
        } else if (StringUtils.contains(tags, "TagD")) {
            return LocalTransactionState.ROLLBACK_MESSAGE; // 回查后丢弃
        } else {
            return LocalTransactionState.UNKNOW; // TagE 超时后丢弃
        }
    }
}
