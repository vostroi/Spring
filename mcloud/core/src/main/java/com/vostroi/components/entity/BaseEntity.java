package com.vostroi.components.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/*
 * MappedSuperclass
 *  1.标记实体类的父类（该父类通常抽象出通用的属性）
 *  2.被标记的该类将不再是一个完整的实体类，不会映射到数据库，但其属性都将映射到子类的数据库表中
 *  3.被标记的该类将不能再标记@Entity，@Table
 */
/**
 * @author Administrator
 * @title: BaseEntiry
 * @projectName mcloud
 * @description: TODO
 * @date 2020/7/1617:19
 */
@MappedSuperclass
@Data
public class BaseEntity extends BaseEntitySimple {
    @Column(name = "crt_time" , columnDefinition = "comment '创建时间' ")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date crtTime;

    @Column(name = "lst_updt_time" , columnDefinition = "comment '最后修改时间' ")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lstUpdtTime;

    @Column(name = "who_crt" , columnDefinition = "char(32) comment '创建者' ")
    private String whoCrt;

    @Column(name = "who_lst_updt" , columnDefinition = "char(32) comment '最后修改者' ")
    private String whoLstUpdt;

}
