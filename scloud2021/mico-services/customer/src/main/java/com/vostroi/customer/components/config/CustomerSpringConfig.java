package com.vostroi.customer.components.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2021/6/13 18:44
 * @projectName scloud2021
 * @title: SpringConfig
 * @description: 统一配置对所有provider调用负载均衡策略
 */
@Configuration
public class CustomerSpringConfig {

    @Bean
    public IRule ribbonRule(){
        return new BestAvailableRule();
    }


}
