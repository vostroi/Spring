package com.ly.lettuce;

import com.ly.lettuce.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.ly.lettuce.entity","com.ly.lettuce.config"})
public class LettuceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LettuceApplication.class, args);
    }

}
