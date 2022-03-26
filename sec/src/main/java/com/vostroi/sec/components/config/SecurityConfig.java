package com.vostroi.sec.components.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * @author Administrator
 * @date 2020/12/16 17:13
 * @projectName sec
 * @title: SecurityConfig
 * @description: 配置 SpringSecurity
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.info("SecurityConfig AuthenticationManager ...");
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        log.info("SecurityConfig UserDetailsService ...");
        String passwrodEncrypt = "{bcrypt}" + new BCryptPasswordEncoder().encode("1008611");
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user_1").password(passwrodEncrypt).authorities("USER").build());
        manager.createUser(User.withUsername("user_2").password(passwrodEncrypt).authorities("USER").build());
        return manager;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("SecurityConfig configure HttpSecurity ...");
        http
            .requestMatchers().anyRequest()
            .and()
            .authorizeRequests().antMatchers("/oauth/**").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        // 配置不拦截静态资源
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }
}
