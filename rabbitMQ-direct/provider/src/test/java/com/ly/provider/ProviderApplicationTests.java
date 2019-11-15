package com.ly.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderApplicationTests {

    @Autowired
    private ProviderLogInfo providerLogInfo;

    @Autowired
    private ProviderLogError providerLogError;

    @Test
    void contextLoads() {
    }

    /**
     * 测试log.info消息推送
     * @throws InterruptedException
     */
    @Test
    void testPush() throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            providerLogInfo.push(System.currentTimeMillis()+"");

            Thread.sleep(1000);
            providerLogError.push(System.currentTimeMillis()+"");
        }
    }
}
