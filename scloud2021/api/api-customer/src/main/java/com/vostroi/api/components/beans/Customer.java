package com.vostroi.api.components.beans;

import com.vostroi.components.entity.BaseEntity;
import com.vostroi.util.EnumConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @date 2021/5/16 14:47
 * @projectName scloud2021
 * @title: Customer
 * @description: 会员 所有终端注册的会员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    private String name;

    private String nickName;

    private Integer gender;

    private Long mobile;

    private EnumConstant.TERMINAL terminal;
}
