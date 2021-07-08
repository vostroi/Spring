package com.vostroi.api.customer.service.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Administrator
 * @date 2021/7/5 23:58
 * @projectName scloud2021
 * @title: Source
 * @description: Stream 消息发送通道 定义
 */
public interface Source {
    String OUTPUT = "my_output";

    @Output()
    MessageChannel output();
}
