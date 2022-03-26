package com.pdd.components.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

/**
 * 应用程序配置类
 * @author  vostroi
 */
@Slf4j
@Configuration
public class AppConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域的路径
        registry.addMapping("/**")
                // 允许跨请求的域名
                .allowedOrigins("*")
                // 是否允许证书
                .allowCredentials(true)
                // 允许跨域的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /**
     * webjars资源解释
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("/webjars/")
//                .resourceChain(false)
//                .addResolver(new WebJarsResourceResolver())
//                .addResolver(new PathResourceResolver());
//    }

    @Bean()
    public CommandLineRunner runner200(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("before application run finish 200");
            }
        };
    }

    @Bean
    public CommandLineRunner runner100(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("before application run finish 100");
            }
        };
    }

}
