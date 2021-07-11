package com.vostroi.api.service.mq;

/**
 * @author Administrator
 * @date 2021/7/6 22:25
 * @projectName scloud2021
 * @title: SimpleMessageProvider
 * @description: 测试 stream 发送到 mq
 */
public interface SimpleMessageProvider {

    String send(String msg);
}
