package com.vostroi.components.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @date 2020/10/18 17:48
 * @projectName mcloud
 * @title: HandHystrixController
 * @description: 测试-cloud gateway 处理 降级  跳转
 */
@RestController
@RequestMapping(value = "/request-error")
public class HandHystrixController {

    @RequestMapping(value = "/hystrix")
    public String handHystrix(HttpServletRequest request , HttpServletResponse response, Exception e){
        int i = 0;
        i++;
        return "Spring Cloud Gateway 处理服务降级跳转..."+System.currentTimeMillis();
    }

}
