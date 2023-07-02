package com.config;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

// 自定义的发送template
// 用来处理其他topic的消息收发
@ExtRocketMQTemplateConfiguration()
public class ExtRocketMQTemplate extends RocketMQTemplate {
}

