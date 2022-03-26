package com.pdd.components.controller;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Api("系统首页/登出接口")
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    /**
     * 系统主页面
     * @return
     */
    @GetMapping(value = "/main")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model){
        return new ModelAndView("home");
    }

    /**
     * 打开新页面
     * @param request
     * @param response
     * @param model
     * @param view
     * @return
     */
    @GetMapping(value="/opv")
    public ModelAndView openView(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(required = false, defaultValue = "login") String view){
        return null;
    }

    /**
     * 登录页面
     * @param request
     * @param response
     * @param model
     * @return 产生唯一KEY ， 来对应登录时唯一验证码 ， 登录成功后产生token ,二者一起同时作为登录成功后的接口验证，
     */
    @GetMapping(value={"/","/login","/home","/index"})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model){
        String key = UUID.randomUUID().toString();
        model.addAttribute("key", key);
        return new ModelAndView("login",model.asMap());
    }

    /**
     * 登出
     * @param request
     * @param response
     * @param model
     * @return 删除token ， 返回登录页面
     */
    @ApiOperation(value = "登出系统", notes = "退出，注销，切换用户操作")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "token" , value = "token" , required = true, paramType = "query")
    })
    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Model model){
        return new ModelAndView("login",model.asMap());
    }

}
