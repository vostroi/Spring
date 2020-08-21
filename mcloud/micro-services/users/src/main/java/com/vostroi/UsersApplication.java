package com.vostroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @title: UsersApplication
 * @projectName mcloud
 * @description: TODO
 * @date 2020/7/16 17:19
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.vostroi.components.users","com.vostroi.api.users"})
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}
