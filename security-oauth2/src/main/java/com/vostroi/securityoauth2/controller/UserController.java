package com.vostroi.securityoauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/5/3 0:20
 * @projectName security-oauth2
 * @title: UserController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 获取当前登录的用户
     * @param authentication
     * @return
     */
    @GetMapping(value = "/curt")
    public Object getCurrentUser(Authentication authentication) {
        // 实际是 MyUserDetailsServiceImpl 中的封装的用户
        Object principal = authentication.getPrincipal();
        return principal;
    }

}
