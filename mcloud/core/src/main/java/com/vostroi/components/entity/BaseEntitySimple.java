package com.vostroi.components.entity;

import com.vostroi.util.EnumConstant;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Administrator
 * @title: BaseEntitySimple
 * @projectName mcloud
 * @description: TODO
 * @date 2020/7/1617:19
 */
@MappedSuperclass
@Data
public class BaseEntitySimple extends IdEntity {

    @Column(name = "version" , columnDefinition = "int(11) default 0 comment '数据版本号' ")
    private int version = 0;

    /**
     * 标记数据状态 若使用到其它状态 请增加
     */
    @Column(name="state" , columnDefinition =  "int(4) default 1 comment '数据状态' ")
    private EnumConstant.RECORD_STATE state = EnumConstant.RECORD_STATE.VALID;
}
