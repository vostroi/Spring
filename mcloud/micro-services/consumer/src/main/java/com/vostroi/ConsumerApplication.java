package com.vostroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2020/8/25 16:33
 * @projectName mcloud
 * @title: ConsumerApplication
 * @description: TODO
 */
@EnableEurekaClient
@EnableAutoConfiguration
//@EnableDiscoveryClient    // 使用 feign 时， 似乎不用加
@EnableFeignClients
@EnableHystrix
@ComponentScan(basePackages = {"com.vostroi.components","com.vostroi.api"})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
