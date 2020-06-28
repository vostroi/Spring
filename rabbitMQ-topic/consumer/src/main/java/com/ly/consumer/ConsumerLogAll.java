package com.ly.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * log.info 队列消息 消费者
 */
@Component
@Slf4j
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${mq.config.queue.log.all}"),
        exchange = @Exchange(value = "${mq.config.exchange.topic.log}", type = ExchangeTypes.TOPIC),
        key = "${mq.config.routing.key.log.all}"
))
public class ConsumerLogAll {
    /**
     * 处理接收到的消息
     * @param message
     */
    @RabbitHandler
    public void process(String message) {
        log.warn("ConsumerLogAll process {} ..." , message );
    }
}
