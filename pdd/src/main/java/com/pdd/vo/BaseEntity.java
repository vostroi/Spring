package com.pdd.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 实体基类
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id" , columnDefinition = " char(38)  not null comment 'UUID' ")
    private String id;

    @Column(name = "createtime" , columnDefinition = " datetime ")
    private String createTime;

    @Column(name = "state" , columnDefinition = " int(2) comment '数据状态' ")
    private Integer state=1;

    @Column(name = "version" , columnDefinition = " decimal(3,2) comment '数据版本号' ")
    private Float version;
}
