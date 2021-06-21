package com.vostroi.customer.components.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2021/6/21 21:58
 * @projectName scloud2021
 * @title: FeignConfig
 * @description: OpenFeign配置类
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLeve(){
        /**
         * NONE 默认 不输出日志
         * BASIC 仅记录请求方法，URL，时间，响应状态
         * HEADERS BASIC 和请求及响应的头信息
         * FULL HEADERS 和 请求及响应的正文及元数据
         */
        return Logger.Level.FULL;
    }
}



