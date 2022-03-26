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
// 开启Hystrix 和 @EnableHystrix 都能开启 Hystrix
@EnableCircuitBreaker
// 这里的包扫描也是麻烦，FeignClient不能被ComponentScan 扫描到
@EnableFeignClients(basePackages = {"com.vostroi.api.feign.order"})             // 开启OpenFeign
@ComponentScan(basePackages = {"com.vostroi.product.components","com.vostroi.api.components","com.vostroi.components"})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}