package com.vostroi.securityoauth2.config;

import com.vostroi.securityoauth2.comm.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

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
    @Autowired
    @Qualifier("redisTokenStore")
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // clients 是请求授权的第三方应用，正常情况是第三方应用找授权服务器注册后，才会有client_id, client_secret
        // 测试为了方便，配置成常量
        clients.inMemory()
                // 客户端ID（第三方应用）
                .withClient(Constants.CLIENT_ID)
                // 客户端密钥
                .secret(Constants.CLIENT_SECRET)
                // 重定向地址
                .redirectUris("https://www.baidu.com/")
                // 授权范围
                .scopes("all")
                // 授权码模式
                .authorizedGrantTypes(Constants.AUTHORIZATION_CODE)

        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore);
    }
}
