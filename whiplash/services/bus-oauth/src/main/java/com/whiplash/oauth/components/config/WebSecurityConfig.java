package com.whiplash.oauth.components.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Administrator
 * @date 2021/9/19 10:52
 * @projectName whiplash
 * @title: SecurityConfig
 * @description: SpringSecurity 相关配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置 spring security 要拦截 放行的资源路径
     * 同时：在使用oauth2时，配置资源服务器时（继承 ResourceServerConfigurerAdapter）也需要配置，需要注意重复配置的问题
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 配置 所有endpoint 放行
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                // 配置获取公钥的接口 放行
                .antMatchers("/api/rsa/publickey").permitAll()
                // 所有请求 必须经过认证（除了前面的配置）
                .anyRequest().authenticated()
        ;
    }

    /**
     * 认证管理器（有很多种类）：默认实现是 ProviderManager
     * 密码模式时 使用 AuthenticationManager
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码编码器 BCryptPasswordEncoder SpringSecurity默认推荐
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
