package com.ly.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 模拟商品服务产生日志
 */
@Component
public class ProviderProduct {

    @Value("${mq.config.exchange.topic.log}")
    private String topicExchange;

    @Value(("${mq.config.routing.key.log.info.product}"))
    private String routingKeyLogInfoProduct;

    @Value(("${mq.config.routing.key.log.warn.product}"))
    private String routingKeyLogWarnProduct;

    @Value(("${mq.config.routing.key.log.debug.product}"))
    private String routingKeyLogDebugProduct;

    @Value(("${mq.config.routing.key.log.error.product}"))
    private String routingKeyLogErrorProduct;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 向队列推送消息
     * 分别推送info debug error warn日志消息
     * @param message
     */
    public void push(String message) {
        /**
         * 参数一：交换器
         * 参数二：路由键
         * 参数三：消息内容
         */
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogInfoProduct, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogWarnProduct, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogErrorProduct, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogDebugProduct, message);
    }
}
