package com.vostroi.securityoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Administrator
 * @date 2021/5/2 23:58
 * @projectName security-oauth2
 * @title: ResourceServceConfig
 * @description: 模拟oauth2资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                // 配置要放行的资源
            .and()
                // 获取 RequestMatcherConfigurer 并匹配允许过滤的请求 （等同于 .authorizeRequests().antMatchers("").permitAll() ）
                .requestMatchers().antMatchers("/user/**")
        ;
    }
}
