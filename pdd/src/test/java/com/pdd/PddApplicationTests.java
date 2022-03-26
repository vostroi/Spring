package com.pdd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PddApplicationTests {

    @Test
    void contextLoads() {
    }


    public static void main(String[] args) {
//        String st1 = "abc";
//        String st2 = "abc";
//        String str3 = new String("abc");
//        System.out.println(st1 == st2);
//        System.out.println(st1.equals(st2));
//        System.out.println(st1 == str3);
//        System.out.println(st1.equals(str3));

        String s1 = "ab";
        String s2 = "c";
        String s3 = "abc";
        String s4 = s1 + s2;
        String s5 = new String("abc");
        System.out.println(s1.getClass().getName() + "@" + s1.hashCode());
        System.out.println(s2.getClass().getName() + "@" + s2.hashCode());
        System.out.println(s3.getClass().getName() + "@" + s3.hashCode());
        System.out.println(s4.getClass().getName() + "@" + s4.hashCode());
        System.out.println(s5.getClass().getName() + "@" + s5.hashCode());
        System.out.println(s3 == s4);
    }




}
