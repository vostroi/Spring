package com.ly.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 模拟订单服务产生日志
 */
@Component
@Slf4j
public class ProviderOrder {

    @Value("${mq.config.exchange.topic.log}")
    private String topicExchange;

    @Value(("${mq.config.routing.key.log.warn.order}"))
    private String routingKeyLogWarnOrder;

    @Value(("${mq.config.routing.key.log.info.order}"))
    private String routingKeyLogInfoOrder;

    @Value(("${mq.config.routing.key.log.debug.order}"))
    private String routingKeyLogDebugOrder;

    @Value(("${mq.config.routing.key.log.error.order}"))
    private String routingKeyLogErrorOrder;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 向队列推送消息
     * 分别推送info debug error日志消息
     * @param message
     */
    public void push(String message) {
        /**
         * 参数一：交换器
         * 参数二：路由键
         * 参数三：消息内容
         */
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogInfoOrder, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogWarnOrder, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogErrorOrder, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogDebugOrder, message);
    }
}
