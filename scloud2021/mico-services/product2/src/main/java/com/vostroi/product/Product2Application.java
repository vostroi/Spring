package com.vostroi.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.vostroi.api.feign.order"})             // 开启OpenFeign
@ComponentScan(basePackages = {"com.vostroi.product.components","com.vostroi.api.components","com.vostroi.components"})
public class Product2Application {

    public static void main(String[] args) {
        SpringApplication.run(Product2Application.class, args);
    }

}
