package com.vostroi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Administrator
 * @date 2021/6/28 23:46
 * @projectName scloud2021
 * @title: ConfigCenterApplication
 * @description: TODO
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }

}
