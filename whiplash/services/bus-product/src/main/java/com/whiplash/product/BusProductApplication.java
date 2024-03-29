package com.whiplash.product;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableCaching
@EnableEncryptableProperties
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.whiplash.components.member.feign","com.whiplash.components.order.feign"})
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.whiplash.components.product.bean"})
@ComponentScan(basePackages = {"com.whiplash.components.tool","cn.hutool.extra.spring","com.whiplash.product.components","com.whiplash.core.handler"})
public class BusProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusProductApplication.class, args);
    }

}
