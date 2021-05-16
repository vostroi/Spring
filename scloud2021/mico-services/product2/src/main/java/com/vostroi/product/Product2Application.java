package com.vostroi.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.vostroi.product.components","com.vostroi.api.product","com.vostroi.components"})
public class Product2Application {

    public static void main(String[] args) {
        SpringApplication.run(Product2Application.class, args);
    }

}
