package com.vostroi.securityapp01.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/5/4 2:26
 * @projectName security-app01
 * @title: UserController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping(value = "/curt")
    public Object getCurrentUser(Authentication authentication) {
    return authentication.getPrincipal();
    }
}
