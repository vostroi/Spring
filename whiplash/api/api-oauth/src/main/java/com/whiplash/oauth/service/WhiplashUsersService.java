package com.whiplash.oauth.service;

import com.whiplash.components.oauth.bean.WhiplashUsers;
import com.whiplash.core.platform.service.BaseService;

/**
 * @author Administrator
 * @date 2021/9/12 18:23
 * @projectName whiplash
 * @title: WhiplashUsersService
 * @description: TODO
 */
public interface WhiplashUsersService extends BaseService<WhiplashUsers, Long> {

    WhiplashUsers getByUserName(String userName);

}
