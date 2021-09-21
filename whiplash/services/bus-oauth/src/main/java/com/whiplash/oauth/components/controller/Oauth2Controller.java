package com.whiplash.oauth.components.controller;

import com.whiplash.core.commom.util.OauthConstant;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.dto.Oauth2TokenDto;
import jdk.internal.util.xml.impl.ReaderUTF8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/19 23:28
 * @projectName whiplash
 * @title: Oauth2Controller
 * @description: 自定义 获取 token 接口
 */
@RestController
@RequestMapping(value = "/oauth")
public class Oauth2Controller {

    @Autowired private TokenEndpoint tokenEndpoint;

    /**
     * 登录认证
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @RequestMapping(value = "/token" , method = {RequestMethod.POST})
    public ResultData<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder().token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .tokenHeader(OauthConstant.BEARER_WITH_SPACE)
                .expiredInS(oAuth2AccessToken.getExpiresIn()).build();
        return ResultData.getResultDataSuccess(oauth2TokenDto);
    }

}
