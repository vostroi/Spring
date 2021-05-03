package com.vostroi.jjwt.config;

import com.vostroi.jjwt.common.Constants;
import com.vostroi.jjwt.service.security.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/5/2 23:58
 * @projectName security-oauth2
 * @title: AuthorizationServerConfig
 * @description: 模拟oauth2授权服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired @Qualifier("jwtTokenStore") private TokenStore tokenStore;
    // AccessTokenConverter 和 JWTTokenEnhance 都是 TokenEnhancer 实现类， 注入时候需要注意
    @Autowired private AccessTokenConverter jwtAccessTokenConverter;
    @Autowired private JWTTokenEnhancer jwtTokenEnhancer;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private MyUserDetailsServiceImpl userDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // clients 是请求授权的第三方应用，正常情况是第三方应用找授权服务器注册后，才会有client_id, client_secret
        // 测试为了方便，配置成常量 oauth2的token放在内存中
        clients.inMemory()
                // 客户端ID（第三方应用）
                .withClient(Constants.CLIENT_ID)
                // 客户端密钥
                .secret(Constants.CLIENT_SECRET)
                // 重定向地址
                .redirectUris("https://www.baidu.com/")
                // 授权范围
                .scopes("all")
                // 授权码模式 及 刷新令牌
                .authorizedGrantTypes(Constants.AUTHORIZATION_CODE,Constants.AUTHORIZATION_REFRESH_TOKEN)
                // token有效时间 60S
                .accessTokenValiditySeconds(60)
                // 刷新令牌有效时间
                .refreshTokenValiditySeconds(60*10)

        ;

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add((TokenEnhancer) jwtAccessTokenConverter);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(delegates);

        endpoints
                // 刷新令牌需要
                .authenticationManager(authenticationManager)
                // 刷新令牌需要
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                // oauth2 的 access_token转换成 jwt token
                .accessTokenConverter(jwtAccessTokenConverter)
                // 自定义扩展 JWT TOKEN
                .tokenEnhancer(tokenEnhancerChain)
        ;

    }
}
