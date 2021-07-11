package com.vostroi.customer.components.service.impl.mq;

import com.vostroi.api.service.mq.SimpleMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author Administrator
 * @date 2021/7/6 22:26
 * @projectName scloud2021
 * @title: SimpleMessageProviderImpl
 * @description: TODO
 */
@EnableBinding(Source.class)
public class SimpleMessageProviderImpl implements SimpleMessageProvider {

    /**
     * 消息发送通道
     */
    @Autowired
    private MessageChannel output;


    @Override
    public String send(String msg) {
        Message<String> message = MessageBuilder.withPayload(msg).build();
        boolean send = false;
        send = output.send(message);
//        send = cust_output.send(message);
        return send+"";
    }
}
