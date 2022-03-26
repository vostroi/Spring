package com.whiplash.oauth.components.service;

import cn.hutool.core.collection.CollUtil;
import com.whiplash.components.oauth.bean.WhiplashUsers;
import com.whiplash.core.platform.dao.BaseDao;
import com.whiplash.oauth.components.dao.WhiplashUsersDao;
import com.whiplash.oauth.service.WhiplashUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2021/9/12 18:25
 * @projectName whiplash
 * @title: WhiplashUsersServiceImpl
 * @description:
 */
@Service
public class WhiplashUsersServiceImpl implements WhiplashUsersService {
    @Autowired private WhiplashUsersDao wuDao;

    @Override
    public BaseDao<WhiplashUsers, Long> getDao() {
        return wuDao;
    }

    @Override
    public WhiplashUsers getUseCache(Long aLong) {
        return null;
    }


    @Override
    public WhiplashUsers getByUserName(String userName) {
        WhiplashUsers wu = wuDao.findByUserName(userName);
        // 设置权限数据
        wu.setRoles(CollUtil.toList("ADMIN"));

        return wu;
    }

}
