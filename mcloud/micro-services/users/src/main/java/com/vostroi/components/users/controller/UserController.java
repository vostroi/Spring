package com.vostroi.components.users.controller;

import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.vostroi.api.users.bean.User;
import com.vostroi.api.users.service.UserService;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired private UserService userService;

    @GetMapping(value = "/conn/{params}")
    public Object testConnect(@PathVariable("params") String params){

        return new Date() + " : " + params;
    }

    @GetMapping(value = "/get/{id}")
    public ResultData getUser(@PathVariable("id") String userId){
        User user = userService.findById(userId);
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "" , user);
    }

    @PostMapping(value = "/add")
    public ResultData addUser(User user){
        if(user == null){
            return ResultData.getResultData(EnumConstant.RESULT_CODE.FA_2222 , "user is null");
        }

        user = userService.save(user);

        if(user==null || StrUtil.isBlank(user.getId())){
            return ResultData.getResultData(EnumConstant.RESULT_CODE.FA_2222 , "添加User失败");
        }

        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "" , user);

    }

}