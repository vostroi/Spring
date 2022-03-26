package com.whiplash.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author Administrator
 * @date 2021/9/19 23:30
 * @projectName whiplash
 * @title: Oauth2TokenDto
 * @description: 封装 oauth2生成的 token信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Oauth2TokenDto {

    /**
     * 令牌
     */
    private String token;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     *访问 令牌 头  前缀
     */
    private String tokenHeader;

    /**
     * 有效时间（秒）
     */
    private int expiredInS;


}
