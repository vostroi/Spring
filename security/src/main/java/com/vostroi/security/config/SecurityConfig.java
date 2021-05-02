package com.vostroi.security.config;

import com.vostroi.security.handler.AccessForbiddenHandler;
import com.vostroi.security.service.security.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author Administrator
 * @date 2021/4/24 22:18
 * @projectName security
 * @title: SecurityConfig
 * @description:  SpringSecurity相关配置
 * 配置PasswordEncoder
 * 配置自定义登录页面，哪些路径需要认证才能访问，哪些不需认证就能访问， 需要继承WebSecurityConfigurerAdapter
 *
 * 基于注解权限控制
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true , prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private AccessForbiddenHandler accessForbiddenHandler;
    @Autowired private DataSource dataSource;
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

    // 配置remember-me
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 初次启动 配置  建表  persistent_logins
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
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
                .antMatchers("/login_page.html" , "/toLogin", "/doLogout").permitAll()
                // 匹配要放行的静态资源
                .antMatchers("/css/**","/js/**","/image/**","/icon/**").permitAll()
                // 正则表达式匹配
                //.regexMatchers("/").permitAll()


                // 匹配角色 严格区分大小写 ( 这里不能以ROLE_开头)
                // .antMatchers("/adm/**").hasRole("ADMIN")
                // access表达式写法--是权限控制的底层实现
                // .antMatchers("/adm/**").access("hasRole('ADMIN')")
                // 匹配权限 严格区分大小写
                // .antMatchers("/adm/**").hasAuthority("EXPORT")
                // .antMatchers("/**").hasIpAddress("127.0.0.1")







                // 基于请求URI的权限匹配（第一次测试未通过，是因为前面权限匹配放行了）
//                .anyRequest().access("@myAccessServiceImpl.hasPermission(request , authentication)")
                // 所有请求都必须认证才放行（与上一行冲突，不能同时配置）
                .anyRequest().authenticated()
        ;

        // 配置 rememberme 前台要增加参数 remember-me
        http.rememberMe().tokenRepository(persistentTokenRepository())
                // 配置 rememberme的参数名
                // .rememberMeParameter()
                // 失效时间 默认2周
                .tokenValiditySeconds(60*10)
                .userDetailsService(userDetailsService)
        ;

        /**
         * 自定义退出
         */
        http.logout()
                // 处理退出登录的方法-必须与前台请求退出的接口地址一样，与 loginProcessingUrl 一致(默认是logout，不用配置)
                .logoutUrl("/dddddOut")
                // 退出登录后（controller 此地址必须放行）
                .logoutSuccessUrl("/doLogout")

        ;
        // 处理无权限访问请求
        http.exceptionHandling().accessDeniedHandler(accessForbiddenHandler);

        /**
         * 默认是开启的 csrf() 跨站请求伪造 http是无状态的，服务端通过记录cookie（里面又存放了session id）来识别客户端，通信中session id可能会被劫持
         * csrf为保证不是其它第三方网站访问，要求请求携带参数：_csrf=token（token服务端产生），如果参数匹配则正常访问
         * 若非前后端分离，thymeleaf在表单中增加_csrf
         * 若前后端分离，不使用cookie
         */
        http.csrf().disable();
    }
}
