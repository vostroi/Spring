package com.whiplash.dto;

import com.whiplash.core.commom.util.EnumConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/19 22:54
 * @projectName whiplash
 * @title: UserDto
 * @description: TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto {

    private Long id;

    private String userName;

    private String password;

    /**
     * 状态
     */
    private EnumConstant.RECORD_STATE state;

    /**
     * 是否有效
     */
    private Boolean enabled;

    /**
     * 菜单
     */
    //private List<Menu> menus;

    private List<String> roles;


}
