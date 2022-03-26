package com.whiplash.elastic;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @date 2021/12/7 18:03
 * @projectName whiplash
 * @title: ElasticSearchApplication
 * @description: TODO
 */
@EnableEncryptableProperties
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.whiplash.components.product.feign"})
@EntityScan(basePackages = {"com.whiplash.components.elastic.bean"})
@ComponentScan(basePackages = {"cn.hutool.extra.spring","com.whiplash.elastic.components","com.whiplash.core.handler"})
public class ElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class);
    }


}
