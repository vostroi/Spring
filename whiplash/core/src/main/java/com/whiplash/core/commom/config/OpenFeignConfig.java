package com.whiplash.core.commom.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2021/9/25 17:04
 * @projectName whiplash
 * @title: OpenFeignConfig
 * @description:
 */
@Configuration
public class OpenFeignConfig {

    /**
     * 配置 feign 日志
     *
     * NONE 默认 不输出日志
     * BASIC 仅记录请求方法，URL，时间，响应状态
     * HEADERS BASIC 和请求及响应的头信息
     * FULL HEADERS 和 请求及响应的正文及元数据
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
