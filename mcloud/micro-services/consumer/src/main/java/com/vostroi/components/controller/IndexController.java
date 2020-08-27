package com.vostroi.components.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import com.vostroi.api.users.bean.User;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/8/25 16:32
 * @projectName mcloud
 * @title: IndexController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {
    private static final String SERVICE_USERS_DOMAIN = "http://127.0.0.1:10086/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/conn/{para}")
    public ResultData connect(@PathVariable(name = "para") String para) {
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000, para);
    }

    @GetMapping(value = "/get/usr/{id}")
    public ResultData getUser(@PathVariable("id") String id){
       return restTemplate.getForObject(SERVICE_USERS_DOMAIN + "usr/get/"+id , ResultData.class);
    }

    @PostMapping(value = "/add/usr")
    public ResultData addUser(@RequestBody User user){
        if(user == null){
            return ResultData.getResultData(EnumConstant.RESULT_CODE.WA_1111 , "数据为空");
        }
        return restTemplate.postForObject(SERVICE_USERS_DOMAIN + "usr/add", user, ResultData.class);
    }
}
