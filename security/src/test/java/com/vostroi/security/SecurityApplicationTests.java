package com.vostroi.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityApplicationTests {

    /**
     * 测试 BCryptPasswordEncoder
     * 密码匹配
     */
    @Test
    void contextLoads() {
        String plaintext = "123456";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 每次加密之后的结果不同
        String passEncoded1 = passwordEncoder.encode(plaintext);
        String passEncoded2 = passwordEncoder.encode(plaintext);
        String passEncoded3 = passwordEncoder.encode(plaintext);
        System.out.println(passEncoded3);


        // passwordEncoder.matches(原始密码--明文密码 , 加密后的密码)
        System.out.println(passwordEncoder.matches(plaintext , passEncoded1));
        System.out.println(passwordEncoder.matches(plaintext , passEncoded2));
        System.out.println(passwordEncoder.matches(plaintext , passEncoded3));


    }

}
