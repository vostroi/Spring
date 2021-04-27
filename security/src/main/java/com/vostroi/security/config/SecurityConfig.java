package com.vostroi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Administrator
 * @date 2021/4/24 22:18
 * @projectName security
 * @title: SecurityConfig
 * @description:  SpringSecurity相关配置
 * 配置PasswordEncoder
 * 配置自定义登录页面，哪些路径需要认证才能访问，哪些不需认证就能访问， 需要继承WebSecurityConfigurerAdapter
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置密码器，官方推荐 BCryptPasswordEncoder
     * 只需要交给IOC管理，SpringSecurity会使用来进行密码匹配
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 自定义登录的参数名
                .usernameParameter("username")
                .passwordParameter("password")
                // 自定义登录页面
                .loginPage("/login_page.html")
                // 必须和前台登录请求接口路径一样（doLogin 必须是是POST请求）
                .loginProcessingUrl("/doLogin")
                /**
                 *登录成功后 后台业务处理（toHome 是 controller 接口 ， 前后端分离直接使用此方法 返回给前台 登录结果）
                 * successForwardUrl 内部是使用的 successHandler方法，security提供了 ForwardAuthenticationSuccessHandler
                 */
                .successForwardUrl("/toHome")
                // 直接跳转到页面 需要自定义 handler
                // .successHandler("静态页面地址")

                /**
                 * 登录失败 后台业务处理 （toLogin 是 controller接口 ）
                 * failureUrl 内部使用的是 failureHandler 方法， security 提供了     public SimpleUrlAuthenticationFailureHandler(String defaultFailureUrl) {
                 */
                .failureUrl("/toLogin")
                // 直接跳转到页面 需要自定义 handler
                // .failureHandler("静态页面地址")
        ;

        /**
         * http.authorizeRequests().anyRequest().authenticated()
         * 只配置上面两行，会出现错误，重定向次数过多，因为拦截了所有请求，包括login_page.html，但login_page.html又是自定义的登录页，形成死循环
         * 需要再配置拦截放行的路径（无须认证就能访问）（不能放.anyRequest().authenticated()后面）
         */
        // 配置对请求的授权
        http.authorizeRequests()
                // 匹配要放行的路径
                .antMatchers("/login_page.html" , "/toLogin").permitAll()
                // 所有请求都必须认证才放行
                .anyRequest().authenticated()

        ;

        http.csrf().disable();
    }
}
