package com.vostroi.api.users.service;

import com.vostroi.api.users.bean.User;
import com.vostroi.components.service.BaseService;

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
