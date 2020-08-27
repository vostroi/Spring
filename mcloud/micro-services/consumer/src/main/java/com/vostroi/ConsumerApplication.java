package com.vostroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2020/8/25 16:33
 * @projectName mcloud
 * @title: ConsumerApplication
 * @description: TODO
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.vostroi.components"})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
