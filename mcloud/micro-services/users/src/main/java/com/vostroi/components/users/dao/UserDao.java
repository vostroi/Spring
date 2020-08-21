package com.vostroi.components.users.dao;

import com.vostroi.api.users.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2020/8/9 13:13
 * @projectName mcloud
 * @title: UserDao
 * @description: TODO
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {
}
