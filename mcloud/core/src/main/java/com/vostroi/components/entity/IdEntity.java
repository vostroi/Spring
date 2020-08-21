package com.vostroi.components.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Administrator
 * @title: IdEntity
 * @projectName mcloud
 * @description: 实体的超类 ID ， 实际id值在BaseService的save方法中指定
 * @date 2020/7/1617:40
 */
@MappedSuperclass
@Data
public class IdEntity implements Serializable {

    @Id
    /**
     * org.springframework.data.annotation.Id 是Spring用来支持非关系型数据库持久化
     */
    @org.springframework.data.annotation.Id
    /**
     * hibernate主键生成策略的扩展 UUID 不带横线 “-”
     */
//    @GenericGenerator(name="system-uuid",strategy="uuid")
    /**
     * @GeneratedValue()  默认的是AUTO 自动选择适合底层数据库的主键策略
     */
//    @GeneratedValue(generator = "system-uuid")
    @Column(name="id" , columnDefinition = "char(32) primary" )
    private String id;
}
