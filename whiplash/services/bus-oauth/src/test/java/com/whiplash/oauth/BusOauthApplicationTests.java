package com.whiplash.oauth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BusOauthApplicationTests {

    @Test
    void contextLoads() {




    }

    public static void main(String[] args) {

        BCryptPasswordEncoder bcry = new BCryptPasswordEncoder();
        String encode1 = bcry.encode("123456");
        String encode2 = bcry.encode("123456");
        String encode3 = bcry.encode("123456");

        System.out.println("encode1="+encode1+",encode2="+encode2+",encode3="+encode3);

        boolean matches = bcry.matches("123456", "$2a$10$rYUQQvf6s5w4tdnwqffTjOh5S.UdNqKCCnluTThkl5XyKvsAiHVly");

        System.out.println(matches);

    }


}
