package com.vostroi.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Administrator
 * @date 2021/5/16 16:49
 * @projectName scloud2021
 * @title: EurekaServer01Application
 * @description: TODO
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer03Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer03Application.class, args);
    }
}
