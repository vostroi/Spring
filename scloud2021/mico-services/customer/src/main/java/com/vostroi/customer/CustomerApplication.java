package com.vostroi.customer;

import com.vostroi.custom.ProductServiceRibbonRule;
import com.vostroi.util.MicroServiceName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * EnableDiscoveryClient：可以在组件中（controller）注入 DiscoveryClient 来获取服务的信息
 */
@SpringBootApplication
/**
 * 针对不同的provider定制制裁均衡策略
 * 注意 不能与 com.vostroi.customer.components.config.CustomerSpringConfig 重复
 */
@RibbonClients({
            @RibbonClient(name = MicroServiceName.MICRO_SERVER_PRODUCT_NAME, configuration = ProductServiceRibbonRule.class)
            // ,@RibbonClient(name = "",configuration = )
        })
//@EnableEurekaClient //使用zookeeper，注释eureka
@EnableDiscoveryClient  // 使用zookeeper或者consul作为注册中心时，注册服务
@ComponentScan(basePackages = {"com.vostroi.customer.components","com.vostroi.api.customer","com.vostroi.components"})
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
