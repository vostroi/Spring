package com.vostroi.security.service.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @date 2021/5/2 10:46
 * @projectName security
 * @title: MyAccessService
 * @description: 自定义access请求控制，控制到每一个请求
 */
public interface MyAccessService {

    /**
     * 校验请求的uri是否有权限
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
