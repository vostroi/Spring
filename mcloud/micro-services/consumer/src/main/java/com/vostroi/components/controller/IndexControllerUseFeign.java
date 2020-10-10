package com.vostroi.components.controller;

import com.vostroi.api.users.bean.User;
import com.vostroi.api.users.service.UserService;
import com.vostroi.api.users.service.feign.UserServiceClient;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @date 2020/8/25 16:32
 * @projectName mcloud
 * @title: IndexController
 * @description: 使用 feign 调用微服务
 */
@RestController
@RequestMapping(value = "/index/feign")
public class IndexControllerUseFeign {
    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping(value = "/get/usr/{id}")
    public ResultData getUser(@PathVariable("id") String id){
       return userServiceClient.getUserById(id);
    }


    @GetMapping(value = "/cal")
    public ResultData calculatePrice(){
        return userServiceClient.calculatePrice();
    }


}
