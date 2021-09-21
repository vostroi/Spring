package com.whiplash.member.components.controller;

import com.whiplash.components.tool.LoginUserHolder;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/9/19 22:35
 * @projectName whiplash
 * @title: LogginController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/usr")
public class UserController {

    @Autowired private LoginUserHolder loginUserHolder;


    @GetMapping(value = "/curt")
    public ResultData<UserDto> getCurrentLoginUser() {
        return ResultData.getResultDataSuccess(loginUserHolder.getCurrentUser());
    }



}
