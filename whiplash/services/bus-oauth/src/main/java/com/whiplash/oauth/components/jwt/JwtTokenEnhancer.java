package com.whiplash.oauth.components.jwt;

import com.google.common.collect.Maps;
import com.whiplash.oauth.dto.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/15 17:29
 * @projectName whiplash
 * @title: JwtTokenEnhancer
 * @description: JWT 内容增强器 扩展 JWT TOKEN 内容
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

        Map<String , Object> infoMap = Maps.newHashMap();

        // 额外信息
        infoMap.put("id", securityUser.getId());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infoMap);

        return accessToken;
    }
}
