package com.vostroi.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/5/2 9:31
 * @projectName security
 * @title: AdminController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/adm")
public class AdminController {

    @GetMapping(value = "/usrs")
    public String listAllUsers() {
        return "返回系统中所有用户数据";
    }



}
