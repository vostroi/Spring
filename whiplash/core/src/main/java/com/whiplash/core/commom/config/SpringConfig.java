package com.whiplash.core.commom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @date 2021/9/3 16:33
 * @projectName whiplash
 * @title: SpringConfig
 * @description: spring基础公共配置
 */
@Configuration
public class SpringConfig {

//    @Bean(name = "factory")
//    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout(10000);
//        factory.setReadTimeout(4000);
//        return factory;
//    }
//
//    @Bean
//    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
//        return new RestTemplate(factory);
//    }

}
