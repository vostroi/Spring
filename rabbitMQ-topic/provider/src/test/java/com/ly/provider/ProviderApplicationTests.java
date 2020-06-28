package com.ly.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderApplicationTests {

    @Autowired
    private ProviderOrder providerOrder;

    @Autowired
    private ProviderProduct providerProduct;

    @Autowired
    private ProviderUser providerUser;

    @Test
    void contextLoads() {
    }

    /**
     * 测试log.info消息推送
     * @throws InterruptedException
     */
    @Test
    void testPush() throws InterruptedException {
        while (true){
            Thread.sleep(10);
            providerOrder.push(System.currentTimeMillis()+"   providerOrder log -------->");
            Thread.sleep(10);
            providerProduct.push(System.currentTimeMillis()+"   providerProduct log -------->");
            Thread.sleep(10);
            providerUser.push(System.currentTimeMillis()+"   providerUser log -------->");
        }
    }
}
