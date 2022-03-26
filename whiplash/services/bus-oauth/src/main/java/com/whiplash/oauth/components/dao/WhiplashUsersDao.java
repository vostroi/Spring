package com.whiplash.oauth.components.dao;

import com.whiplash.components.oauth.bean.WhiplashUsers;
import com.whiplash.core.commom.util.EnumConstant;
import com.whiplash.core.platform.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2021/9/12 18:27
 * @projectName whiplash
 * @title: WhiplashUsersDao
 * @description: TODO
 */
@Repository
public interface WhiplashUsersDao extends BaseDao<WhiplashUsers, Long> {
    WhiplashUsers findByUserNameAndState(String userName, EnumConstant.RECORD_STATE state);

    WhiplashUsers findByUserName(String userName);
}
