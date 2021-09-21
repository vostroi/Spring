package com.whiplash.oauth.components.config;

import com.google.common.collect.Lists;
import com.whiplash.oauth.components.jwt.JwtTokenEnhancer;
import com.whiplash.oauth.components.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/15 17:19
 * @projectName whiplash
 * @title: Oauth2Config
 * @description: Oauth2授权服务器配置
 * AuthorizationServerConfigurerAdapter 提供给开发者配置
 *  ClientDetailsServiceConfigurer：
 *  AuthorizationServerEndpointsConfigurer：
 *  AuthorizationServerSecurityConfigurer：
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired private UserDetailServiceImpl userDetailsService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * 访问安全配置：为授权服务配置端点（/oauth/****）的安全访问规则。过滤器
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        // 允许表单认证 申请令牌
        security.allowFormAuthenticationForClients();
        // security 的 access 表达式
        // security.tokenKeyAccess("isAuthenticated()");

        // security.checkTokenAccess("permitAll()");





        //
        // security.sslOnly();


    }

    /**
     * 配置 第三 方应用授权
     * clients 正常情况下 第三方应用需要在系统中注册后，系统为其生成 client_id 和 client_secret
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_app").secret(passwordEncoder.encode("vostroi"))
                // 生成token的权限范围
                .scopes("all")
                // 授权类型（oauth2的4种授权类型）
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(10)
                .refreshTokenValiditySeconds(7200)
                // 自动授权 无需客户端确认
                // .autoApprove(true)
        ;


    }


    /**
     * 授权服务器访问端点配置
     * 配置 tokenStroe、tokenEnhancer服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = Lists.newArrayList();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter());

        // 配置 token 增强器
        enhancerChain.setTokenEnhancers(delegates);

        // 配置认证器
        endpoints.authenticationManager(authenticationManager);
        // 配置加载用户的服务
        endpoints.userDetailsService(userDetailsService);

        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        endpoints.tokenEnhancer(enhancerChain);
    }

    /**
     * 获取密钥对 用于 JWT生成 解析 令牌
     * @return
     */
    @Bean
    public KeyPair keyPair() {

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("whiplash.jks"), "whiplash".toCharArray());

        KeyPair whiplash = keyStoreKeyFactory.getKeyPair("whiplash", "whiplash".toCharArray());

        // RSAPublicKey rsaPublicKey = (RSAPublicKey)whiplash.getPublic();
        // RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) whiplash.getPrivate();

        return whiplash;
    }

    /**
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }


}
