package com.vostroi.autosocketclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableScheduling
public class AutoSocketClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoSocketClientApplication.class, args);
    }

}
