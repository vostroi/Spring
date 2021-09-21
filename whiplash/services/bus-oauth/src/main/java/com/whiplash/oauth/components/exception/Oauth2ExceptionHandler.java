package com.whiplash.oauth.components.exception;

import com.whiplash.core.commom.util.ResultData;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author Administrator
 * @date 2021/9/19 23:42
 * @projectName whiplash
 * @title: Oauth2ExceptionHandler
 * @description: 全局处理 Oauth2 抛出的异常
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {

    public ResultData handOauth2Exception(OAuth2Exception o2e) {
        return ResultData.getResultDataError(o2e.getMessage());
    }


}
