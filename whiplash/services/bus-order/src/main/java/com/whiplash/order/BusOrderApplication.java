package com.whiplash.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.whiplash.components.member.feign","com.whiplash.components.product.feign"})
@EntityScan(basePackages = {"com.whiplash.components.order.bean"})
@ComponentScan(basePackages = {"com.whiplash.components.tool","com.whiplash.order.components","cn.hutool.extra.spring","com.whiplash.core.handler"})
public class BusOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusOrderApplication.class, args);
    }

}
