package com.vostroi.jjwt.config;

import com.vostroi.jjwt.common.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Administrator
 * @date 2021/5/3 22:08
 * @projectName security-jjwt
 * @title: JwtConfig
 * @description: JWT配置类
 */
@Configuration
public class JwtConfig {

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    /**
     * 将oauth2生成的token转换成JWT TOKEN
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(Constants.JWT_SECRET);
        converter.setVerifierKey(Constants.JWT_SECRET);
        return converter;
    }
}
