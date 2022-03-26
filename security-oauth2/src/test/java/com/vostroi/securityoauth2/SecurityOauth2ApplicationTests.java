package com.vostroi.securityoauth2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityOauth2ApplicationTests {

    @Test
    void contextLoads() {

        String plaintext = "VOSTROI_SECRET";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 每次加密之后的结果不同
        String passEncoded = passwordEncoder.encode(plaintext);
        System.out.println(passEncoded);

    }
}
