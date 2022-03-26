package com.vostroi.customer.components.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2021/6/13 18:44
 * @projectName scloud2021
 * @title: SpringConfig
 * @description: 统一配置对所有provider调用负载均衡策略
 * 注意 与 ProductServiceRibbonRule  只能保留一个
 */
@Configuration
public class CustomerSpringConfig {

//    @Bean
//    public IRule ribbonRule(){
//        return new RoundRobinRule();      // 默认 轮询
//        return new RetryRule();           // 先按 RoundRobinRule 如果获取服务失败会在指定时间内重试
//        return new BestAvailableRule();   // 会先过滤掉多次访问故障而处于断路器断开状态的服务，然后选择一个并发量较小的服务
//        return new AvailabilityFilteringRule();       // 先过滤掉故障服务 选择并发早较小的服务
//        return new RandomRule();            // 随机
//    }

    /**
     * HystrixDashboard  提示 Unable to connect to Command Metric Stream
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        //访问路径
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
