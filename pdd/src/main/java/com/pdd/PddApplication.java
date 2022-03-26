package com.pdd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.pdd.components"})
public class PddApplication {

    public static void main(String[] args) {
        // 应用以无端口占用方式启动
//        new SpringApplicationBuilder().sources(PddApplication.class).web(WebApplicationType.NONE).run(args);
        SpringApplication.run(PddApplication.class, args);
        log.info("application started ...");
    }

}
