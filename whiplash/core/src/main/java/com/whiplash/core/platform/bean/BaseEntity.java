package com.whiplash.core.platform.bean;

import cn.hutool.core.date.DateUtil;
import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/9/2 13:56
 * @projectName whiplash
 * @title: BaseEntity
 * @description: 抽象基础实体  提取公共属性
 * GenerationType.SEQUENCE 根据底层数据库的序列来生成主键，条件是数据库支持序列
 * GenerationType.AUTO 默认 由程序生成主键
 * GenerationType.IDENTITY 数据库生成 自增
 * GenerationType.TABLE 使用一个特定的数据库表生成主键
 *
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Id
    @org.springframework.data.annotation.Id     // 用于支持非关系性数据库
    @Column(name="id" , columnDefinition = "bigint UNSIGNED NOT NULL AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 数据版本号
     */
    @Column(name="version" , columnDefinition = "int")
    private int version = 0;

    /**
     * 标记数据状态 若使用到其它状态 请增加
     */
    @Column(name="state" , columnDefinition = "int")
    private EnumConstant.RECORD_STATE state = EnumConstant.RECORD_STATE.VALID;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    @Temporal(TemporalType.TIMESTAMP)       // 存入数据库中日期格式 yyyy-MM-dd hh:MM:ss
    private Date crtTime = DateUtil.date();

    /**
     * 最后修改时间
     */
    @Column(name="last_updt_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdtTime = DateUtil.date();

    /**
     * 创建者
     */
    @Column(name = "crt_user" , columnDefinition = "bigint")
    private Long crtUser;

    /**
     * 最后修改者
     */
    @Column(name = "last_updt_user" , columnDefinition = "bigint")
    private Long lastUpdtUser;

    /**
     * 序号
     */
    @Column(name = "order_num" , columnDefinition = "bigint")
    private Long orderNum=0L;

    /**
     * 备注
     */
    @Column(name = "remark" , columnDefinition = "varchar(256)")
    private String remark= CommomConstant.STR_EMPTY;
}
