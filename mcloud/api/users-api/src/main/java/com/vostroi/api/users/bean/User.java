package com.vostroi.api.users.bean;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @JsonAlias 别名，可以配置多个 ； 适用 json 转模型时 （使用模型的属性名也可以）； 但 模型转 json 时，还是以模型的属性名称为准 ；需要属性的getter/setter 方法
 * @JsonProperty 别名 ， 适用 json 转模型（必须与别名保持一致，使用模型的属性名 不行） 和 模型转 json （也以别名为准） ； 不需要 属性的getter/setter
 */
@Entity
@Data
@Table(name="t_users")
public class User extends BaseUser {
    @Column(name = "name" , columnDefinition = "varchar(100) comment '昵称' ")
    @JsonAlias(value = "nickName")
    private String name;

    @Column(name = "real_name" , columnDefinition = "varchar(100) comment '真名' ")
    @JsonAlias(value = "trueName")
    private String realName;

    @Column(name = "mobile" , columnDefinition = "char(11) comment '手机' ")
    @JsonProperty(value = "cellPhone")
    private String mobile;
}
