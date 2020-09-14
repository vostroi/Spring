package com.vostroi.api.users.service;

import com.vostroi.api.users.bean.User;
import com.vostroi.components.service.BaseService;
import com.vostroi.util.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 * @date 2020/8/9 13:05
 * @projectName mcloud
 * @title: UserService
 * @description: UserService
 */
public interface UserService extends BaseService<User , String> {

    User findById(String id);
}
