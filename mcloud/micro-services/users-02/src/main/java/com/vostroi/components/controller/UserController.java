package com.vostroi.components.controller;

import cn.hutool.core.util.StrUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vostroi.api.users.bean.User;
import com.vostroi.api.users.service.UserService;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Administrator
 * @date 2020/7/29 16:29
 * @projectName mcloud
 * @title: UserController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/usr")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 利用 appName 来测试 多节点 负载均衡
     */
    @Value("${spring.application.name:}")
    private String appName;

    /**
     * 利用 appPort 来测试 多节点 负载均衡
     */
    @Value("${server.port}")
    private String appPort;

    @GetMapping(value = "/conn/{params}")
    public Object testConnect(@PathVariable("params") String params) {

        return new Date() + " : " + params;
    }

    @GetMapping(value = "/get/{id}")
    public ResultData getUser(@PathVariable("id") String userId) {
        User user = userService.findById(userId);
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000, appPort + "###user-02", user);
    }

    @PostMapping(value = "/add")
    public ResultData addUser(@RequestBody User user) {
        if (user == null) {
            return ResultData.getResultData(EnumConstant.RESULT_CODE.FA_2222, "user is null");
        }

        user = userService.save(user);

        if (user == null || StrUtil.isBlank(user.getId())) {
            return ResultData.getResultData(EnumConstant.RESULT_CODE.FA_2222, "添加User失败");
        }

        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000, "", user);

    }

    /**
     * 测试 Hystrix 熔断
     * @return
     */
    @GetMapping(value = "/calc")
    @HystrixCommand(fallbackMethod = "requestError")
    public ResultData calculatePrice(){

        int i = 1 / 0;

        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000, "");
    }
}
