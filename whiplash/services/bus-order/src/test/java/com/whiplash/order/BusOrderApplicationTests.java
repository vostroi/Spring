package com.whiplash.order;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BusOrderApplicationTests {
    @Autowired private StringEncryptor encryptor;

    @Test
    void contextLoads() {




    }


    @Test
    void encryptPass() {
        /**
         * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="root" password=whiplash algorithm=PBEWithMD5AndDES
         * UUcDXrhTzvXcjc7ypW/mqQ==
         *
         * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="t51011983" password=whiplash algorithm=PBEWithMD5AndDES
         * guYfbOLDOjbIP974BnUaSnsEmlzkWc1o
         *
         * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="jdbc:mysql://120.25.216.73:3306/whiplash-order?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=true" password=whiplash algorithm=PBEWithMD5AndDES
         * 0kgQIk7Ikh/76FKBYrkrZOlCoXT3R+RA3Yy6N6cuBFDsvCUFNN4azYj8m6YYGaJhZx36DgO+jaEeHrGvVdPb1uxNVZUmfq5oyDJYE6wdHiMuR/dx8BbuTSxLdFN8aaKpI/2Jz2Cizm+6rHVJ4gGMh+fSG7qthcFKXaREF5oypd8hbbCNcVnHCA==
         *
         * spring.datasource.username="ENC(sBdk7fXxdnCZMzPjGkZr0g==)"
         *
         *
         *
         */

        System.out.println("加密后："+encryptor.encrypt("root"));
        System.out.println("解密后：" + encryptor.decrypt("UUcDXrhTzvXcjc7ypW/mqQ=="));




    }

}
