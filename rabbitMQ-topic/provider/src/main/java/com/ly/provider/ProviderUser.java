package com.ly.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 模拟User服务产生info debug error日志
 */
@Component
public class ProviderUser {

    @Value("${mq.config.exchange.topic.log}")
    private String topicExchange;

    @Value(("${mq.config.routing.key.log.info.user}"))
    private String routingKeyLogInfoUser;

    @Value(("${mq.config.routing.key.log.debug.user}"))
    private String routingKeyLogDebugUser;

    @Value(("${mq.config.routing.key.log.warn.user}"))
    private String routingKeyLogWarnUser;

    @Value(("${mq.config.routing.key.log.error.user}"))
    private String routingKeyLogErrorUser;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 向队列推送消息
     * 分别推送info debug error warn 日志消息
     * @param message
     */
    public void push(String message) {
        /**
         * 参数一：交换器
         * 参数二：路由键
         * 参数三：消息内容
         */
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogInfoUser, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogWarnUser, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogErrorUser, message);
        this.amqpTemplate.convertAndSend(topicExchange , routingKeyLogDebugUser, message);
    }
}
