package com.vostroi.order.components.amqp.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2021/7/7 22:53
 * @projectName scloud2021
 * @title: CustomerMessageListener
 * @description: 监听 CUST_EXCHANGE 交换器
 */
@Component
@EnableBinding(Sink.class)
@Slf4j
public class CustomerMessageListener {

    @Value(value = "${server.port:未获取到端口号}")
    private String serverPort;

    /**
     * 消息输入（接收消息）
     * @param msg
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> msg) {
        log.info("消费者 {} 消费消息 {}",serverPort , msg.getPayload());
    }

}
