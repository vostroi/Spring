package com.vostroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @title: UsersApplication
 * @projectName mcloud
 * @description: TODO
 * @date 2020/7/16 17:19
 */
@EnableEurekaClient
@Configuration
@EnableAutoConfiguration
@EnableHystrix  // 和 @EnableCircuitBreaker 都可以开启 Hystrix
@ComponentScan(basePackages = {"com.vostroi.components", "com.vostroi.api.users"})
public class Users01 {

    public static void main(String[] args) {
        SpringApplication.run(Users01.class, args);
    }

}
