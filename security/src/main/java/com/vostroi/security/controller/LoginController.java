package com.vostroi.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
     *
     * Secured 用来验证是否有指定的角色  满足条件 正常执行， 否则提示500错误
     */
    @Secured({"ROLE_ADMIN"})
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
    @RequestMapping("/doLogout")
    public String logout(){
        log.info( "注销登录" );
        return "注销成功";
    }

    /**
     *  @PreAuthorize 表示进入方法之前 进行权限判断
     *  @PostAuthorize 表示结束方法后，进行权限判断 一般很少使用
     * 参考都是权限表达式-access表达式
     * @return
     */
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('EXPORT')")
    @RequestMapping("/doSmt")
    public String doSomething(){
        log.info( "业务逻辑" );
        return "处理成功";
    }

}
