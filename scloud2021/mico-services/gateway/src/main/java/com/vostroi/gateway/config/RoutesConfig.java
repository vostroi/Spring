package com.vostroi.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2021/6/27 18:22
 * @projectName scloud2021
 * @title: RoutesConfig
 * @description: 编码方式配置路由
 */
@Configuration
public class RoutesConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("ms_baidu", p->p.path("/baidu")
                // .filters(f->f.stripPrefix(1)) 跳转路径是 https://www.baidu.com/
                // 不加 .filters(f->f.stripPrefix(1)) 跳转路径是  https://www.baidu.com/baidu
                .filters(f->f.stripPrefix(1))
                .uri("https://baidu.com"));
        routes.route("ms_zhibo",p->p.path("/zhibo")
                // .filters()
                .uri("https://zhibo8.cc"));
        return routes.build();
    }
}
