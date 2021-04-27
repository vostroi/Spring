package com.vostroi.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2021/4/24 21:58
 * @projectName security
 * @title: LoginController
 * @description: TODO
 */
@RestController
@Slf4j
public class LoginController {

    /**
     * 处理登录成功后跳转
     * @param request
     * @return
     */
    @RequestMapping(value="/toHome")
    public String toHome(HttpServletRequest request) {
        log.info( "您登录成功了" );
        return "您登录成功了";
    }

    /**
     * 处理登录失败后跳转
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        log.info( "登录失败" );
        return "登录失败";
    }

    /**
     * 处理注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        log.info( "注销登录" );
        return "注销成功";
    }

}
