package com.vostroi.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Administrator
 * @description 要使用 oauth2 保护应用，主要分为三步
 *              1. 配置资源服务器
 *              2. 配置认证服务器
 *              3. 配置 SpringSecurity
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.vostroi.sec.components"})
public class SecApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecApplication.class, args);
    }

}
