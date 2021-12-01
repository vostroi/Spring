package com.whiplash.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.whiplash.components.oauth.bean"})
@ComponentScan(basePackages = {"com.whiplash.oauth.components","com.whiplash.components.tool","com.whiplash.core.handler"})
public class BusOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusOauthApplication.class, args);
    }

}
