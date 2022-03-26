package com.vostroi.securityapp01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
public class SecurityApp01Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApp01Application.class, args);
    }

}
