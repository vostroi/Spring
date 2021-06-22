package com.vostroi.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
// 开启Hystrix 和 @EnableHystrix 都能开启 Hystrix
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.vostroi.product.components","com.vostroi.api.product","com.vostroi.components"})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
