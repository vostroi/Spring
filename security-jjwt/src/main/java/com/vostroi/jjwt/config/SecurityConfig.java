package com.vostroi.jjwt.config;

import com.vostroi.jjwt.handler.AccessForbiddenHandler;
import com.vostroi.jjwt.service.security.MyUserDetailsServiceImpl;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2021/4/24 22:18
 * @projectName security
 * @title: SecurityConfig
 * @description:  SpringSecurity相关配置
 * oauth2有两个重要服务
 * 1.授权服务
 * 2.资源服务器
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private AccessForbiddenHandler accessForbiddenHandler;
    @Autowired private MyUserDetailsServiceImpl userDetailsService;

    /**
     * 配置密码器，官方推荐 BCryptPasswordEncoder
     * 只需要交给IOC管理，SpringSecurity会使用来进行密码匹配
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 刷新令牌时会使用到
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 配置放行授权服务器的端点（接口） /oauth/**
         */
        // 配置对请求的授权
        http.authorizeRequests()
                // 匹配要放行的路径
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login/**" , "/logout/**").permitAll()
                .anyRequest().authenticated()
            // and()等于http.xxxx
            .and().exceptionHandling().accessDeniedHandler(accessForbiddenHandler)
            .and().formLogin().permitAll()
            .and().csrf().disable()


        ;




        // 处理无权限访问请求
        http.exceptionHandling().accessDeniedHandler(accessForbiddenHandler);


        http.csrf().disable();
    }
}
