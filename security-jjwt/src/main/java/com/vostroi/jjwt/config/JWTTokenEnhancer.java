package com.vostroi.jjwt.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/5/3 23:11
 * @projectName security-jjwt
 * @title: JWTTokenEnhance
 * @description: 自定义扩展JWT TOKEN内容
 */
@Component("jwtTokenEnhancer")
public class JWTTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        HashMap<String, Object> extention = new HashMap<>();
        extention.put("测试扩展", "像个傻子多么好");
        DefaultOAuth2AccessToken doat = (DefaultOAuth2AccessToken) accessToken;
        doat.setAdditionalInformation(extention);
        return doat;
    }
}
