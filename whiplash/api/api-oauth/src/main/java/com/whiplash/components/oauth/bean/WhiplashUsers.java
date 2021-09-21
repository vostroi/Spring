package com.whiplash.components.oauth.bean;

import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/12 18:06
 * @projectName whiplash
 * @title: WhiplashUsers
 * @description: 系统登录用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_whiplash_users")
public class WhiplashUsers extends BaseEntity  {
    @Column(name="member_id")
    private Long member;

    @Column(name="user_name" , columnDefinition = "varchar(64)")
    private String userName;

    @Column(name="pass_word" , columnDefinition = "varchar(128)")
    private String password;

    @Transient
    private List<String> roles;

    //private List<Menu> menus;



}
