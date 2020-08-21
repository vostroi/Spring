package com.vostroi.api.users.bean;

import com.vostroi.components.entity.BaseUser;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Administrator
 * @date 2020/7/17 18:01
 * @projectName mcloud
 * @title: Users
 * @description: 系统用户
 */
@Entity
@Data
@Table(name="t_users")
public class User extends BaseUser {
    @Column(name = "name" , columnDefinition = "varchar(100) comment '昵称' ")
    private String name;

    @Column(name = "real_name" , columnDefinition = "varchar(100) comment '真名' ")
    private String realName;

    @Column(name = "mobile" , columnDefinition = "char(11) comment '手机' ")
    private String mobile;
}
