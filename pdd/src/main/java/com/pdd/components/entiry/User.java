package com.pdd.components.entiry;

import com.pdd.vo.BaseEntity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统用户
 */
@Table(name = "t_user")
@Entity
public class User extends BaseEntity {
    @Column(name = "username" , unique = true , columnDefinition = "varchar(32) not null comment '登录名' ")
    private String userName;

    @Column(name = "dispname" , columnDefinition = "varchar(64) comment '显示名称' ")
    private String dispName;

    @Column(name = "passwd" , columnDefinition = "varchar(64) comment '加密密码'" )
    private String passwd;

    @Column(name = "locked" , columnDefinition = " tinyint(1) comment '锁定' ")
    private boolean locked;


}
