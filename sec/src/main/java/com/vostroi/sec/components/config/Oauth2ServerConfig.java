package com.vostroi.sec.components.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * @author Administrator
 * @date 2020/12/16 17:23
 * @projectName sec
 * @title: Oauth2ServerConfig
 * @description: 客户端模式 ; 配置 资源服务 和 认证服务
 */
@Configuration
@Slf4j
public class Oauth2ServerConfig {
    private static final String DEMO_CLIENT_ID = "ORDER";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        /**
         * 资源安全 相关配置
         * @param resources
         * @throws Exception
         */
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            log.info("Oauth2ServerConfig ResourceServerConfiguration configure ResourceServerSecurityConfigurer config...");
            resources.resourceId(DEMO_CLIENT_ID).stateless(true);
        }

        /**
         * http 安全相关配置
         * @param http
         * @throws Exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            log.info("Oauth2ServerConfig ResourceServerConfiguration configure HttpSecurity config...");
            http
                .csrf().disable()   // 关闭跨域保护
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                    .requestMatchers().anyRequest()
                .and()
                    .anonymous()
                .and()
                    .authorizeRequests()
                    .antMatchers("/open/pro/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                // order 的访问 必须经过认证
                    .antMatchers("/open/ord/**").authenticated()
                .and()
                .logout()
//                    .logoutUrl()
                    .clearAuthentication(true)
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
//                    .addLogoutHandler()
                ;
        }
    }

    @Configuration
    @EnableAuthorizationServer
    public static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        RedisConnectionFactory redisConnectionFactory;

        /**
         * 配置 AuthorizationServer 安全认证相关信息，创建 ClientCredentialsTokenEndpointsFilter （客户端身份认证核心过滤器） 核心过滤器
         * @param security
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            log.info("Oauth2ServerConfig AuthorizationServerConfiguration AuthorizationServerSecurityConfigurer config...");
            security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
            security.allowFormAuthenticationForClients();
        }

        /**
         * 配置 OAuth2 客户端 相关信息
         * 配置两个客户端 一个用于 password认证(client_2)， 一个用于 client 认证 (client_1)
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            log.info("Oauth2ServerConfig AuthorizationServerConfiguration ClientDetailsServiceConfigurer config...");
            clients.inMemory()
                    .withClient("client_1")
                        .resourceIds(DEMO_CLIENT_ID).authorizedGrantTypes("client_credentials", "refresh_token")    // 客户端模式
                        .scopes("select").authorities("client").secret("{bcrypt}" + new BCryptPasswordEncoder().encode("123456"))
                .and()
                    .withClient("client_2")
                        .resourceIds(DEMO_CLIENT_ID).authorizedGrantTypes("password" , "refresh_token")             // 密码模式
                        .scopes("select").authorities("client").secret("{bcrypt}" + new BCryptPasswordEncoder().encode("654321"));
        }

        /**
         * 配置 AuthorizationServerEndpointsConfigurer 相关类， 配置身份认证器， 认证方式， TokenStore , TokenGranter , OAuth2RequestFactory
         * 配置 token 的数据源， 自定义的 TokenService 等
         * @param endpoints
         * @throws Exception
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            log.info("Oauth2ServerConfig AuthorizationServerConfiguration AuthorizationServerEndpointsConfigurer config...");
            /**
             * redis 存储 token
             */
            endpoints
                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET , HttpMethod.POST);

        }
    }


}
