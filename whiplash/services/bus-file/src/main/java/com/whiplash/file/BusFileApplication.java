package com.whiplash.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = {"com.whiplash.core.handler"})
public class BusFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusFileApplication.class, args);
    }

}
