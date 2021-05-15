package com.vostroi.components.entity;

import com.vostroi.util.EnumConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/5/15 16:41
 * @projectName scloud2021
 * @title: BaseEntity
 * @description:
 */
@Data
public class BaseEntity implements Serializable {

    private Long id;

    /**
     * 数据版本号
     */
    private int version = 0;

    /**
     * 标记数据状态 若使用到其它状态 请增加
     */
    private EnumConstant.RECORD_STATE state = EnumConstant.RECORD_STATE.VALID;

    /**
     * 创建时间
     */
    private Date crtTime;

    /**
     * 最后修改时间
     */
    private Date lastUpdtTime;

    /**
     * 创建者
     */
    private String crtUser;

    /**
     * 最后修改者
     */
    private String lastUpdtUser;

    /**
     * 序号
     */
    private Long orderNum;

    /**
     * 备注
     */
    private String remark;
}
