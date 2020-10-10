package com.vostroi;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @author Administrator
 * @date 2020/8/25 16:33
 * @projectName mcloud
 * @title: ConsumerApplication
 * @description: Hystrix Dashboard 监控服务调用
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class ServicesWatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicesWatchApplication.class, args);
    }
}
