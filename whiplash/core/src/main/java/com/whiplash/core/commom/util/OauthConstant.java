package com.whiplash.core.commom.util;

/**
 * @author Administrator
 * @date 2021/9/12 18:43
 * @projectName whiplash
 * @title: OauthConstant
 * @description: 授权模块常量
 */
public interface OauthConstant {

    final String USER_NOT_EXIST = "用户不存在";
    final String USER_DISABLED = "用户不可用";
    final String USER_LOCKED = "用户被锁定";
    final String USER_EXPIRED = "用户已过期";


    final String ACCESS_TOKEN_EXPIRED = "令牌已过期";

    final String AUTHORITY_PREFIX = "ROLE_";
    final String AUTHORITY_CLAIM_NAME = "authorities";

    final String AUTHORIZATION = "Authorization";
    final String BEARER = "Bearer";
    final String BEARER_WITH_SPACE = "Bearer ";
    final String USER = "user";

}
