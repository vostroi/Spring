package com.vostroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Administrator
 * @date 2020/8/30 17:36
 * @projectName mcloud
 * @title: EurekaServerApplication
 * @description: TODO
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer9528 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer9528.class, args);
    }
}
