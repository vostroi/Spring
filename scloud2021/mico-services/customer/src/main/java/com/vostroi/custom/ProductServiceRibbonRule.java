package com.vostroi.custom;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2021/6/16 22:13
 * @projectName scloud2021
 * @title: CustomerRibbonRule
 * @description: TODO
 */
@Configuration
public class ProductServiceRibbonRule {
    @Bean
    public IRule productRule(){
        // return new RandomRule();
        return new AvailabilityFilteringRule();
    }
}
