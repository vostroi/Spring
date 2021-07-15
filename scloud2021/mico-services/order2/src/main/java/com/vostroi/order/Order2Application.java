package com.vostroi.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @date 2021/7/7 22:48
 * @projectName scloud2021
 * @title: Application
 * @description: TODO
 */
@SpringBootApplication
@EnableDiscoveryClient  // 使用zookeeper,consul,nacos 作为注册中心时，注册服务
//@EnableFeignClients(basePackages = {"com.vostroi.api.feign.product","com.vostroi.api.feign.order"})             // 开启OpenFeign
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.vostroi.components","com.vostroi.order.components"})
public class Order2Application {
    public static void main(String[] args) {
        SpringApplication.run(Order2Application.class, args);
    }
}
