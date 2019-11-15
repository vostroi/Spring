package com.ly.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * log.info队列消息生产者
 */
@Component
public class ProviderLogInfo {

    @Value("${mq.config.exchange.direct.log}")
    private String directExchange;

    @Value(("${mq.config.routing.key.log.info}"))
    private String routingKeyLogInfo;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 向队列推送消息
     * @param message
     */
    public void push(String message) {
        /**
         * 参数一：交换器
         * 参数二：路由键
         * 参数三：消息内容
         */
        this.amqpTemplate.convertAndSend(directExchange , routingKeyLogInfo, message);
    }
}
