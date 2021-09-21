package com.whiplash.components.member.bean;

import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstant;
import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Administrator
 * @date 2021/9/2 15:17
 * @projectName whiplash
 * @title: Customer
 * @description: Customer 实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_customer")
public class Customer extends BaseEntity {

    @Column(name = "nick_name" , columnDefinition = "varchar(32)")
    private String nickName = CommomConstant.STR_EMPTY;

    @Column(name = "gender" , columnDefinition = "int")
    private EnumConstant.GENDER gender = EnumConstant.GENDER.UNKNOWN;

    @Column(name = "mobile", columnDefinition = "bigint")
    private Long mobile;

    @Column(name = "country" , columnDefinition = "varchar(32)")
    private String country = CommomConstant.STR_EMPTY;

    @Column(name = "province" , columnDefinition = "varchar(32)")
    private String province = CommomConstant.STR_EMPTY;

    @Column(name = "city" , columnDefinition = "varchar(32)")
    private String city = CommomConstant.STR_EMPTY;

    @Column(name = "real_name" , columnDefinition = "varchar(32)")
    private String realName = CommomConstant.STR_EMPTY;

    @Column(name = "id_code" , columnDefinition = "varchar(32)")
    private String idCode = CommomConstant.STR_EMPTY;

    /**
     * 最好转成短链接存入数据库
     */
    @Column(name = "head_img" , columnDefinition = "varchar(256)")
    private String headImg = CommomConstant.STR_EMPTY;

    @Column(name = "member_level" , columnDefinition = "int")
    private EnumConstant.MEMBER_LEVEL level = EnumConstant.MEMBER_LEVEL.NORMAL;


}
