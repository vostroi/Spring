package com.whiplash.member.components.controller;

import com.whiplash.core.commom.util.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/9/19 23:11
 * @projectName whiplash
 * @title: HelloController
 * @description: TODO
 */
@RestController
@RequestMapping(value="/hello")
public class HelloController {

    @GetMapping(value = "/test")
    public ResultData hello() {
        return ResultData.getResultDataSuccess("Hello world!");
    }


}
