package com.vostroi.nimbusjosejwt.jwt.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/8/31 14:26
 * @projectName nimbus-jose-jwt
 * @title: PayloadDto
 * @description: TODO
 */
@Data
@Entity
@Builder
@Table(name="t_payload")
public class PayloadDto {
    @Id
    @Column(name = "id", columnDefinition = "bigint NOT NULL AUTO_INCREMENT ")
    private long id;

    @ApiModelProperty("主题")
    @Column(name="sub")
    private String sub;

    @ApiModelProperty("签发时间")
    @Column(name="iat")
    private Long iat;

    @ApiModelProperty("过期时间")
    @Column(name="exp")
    private Long exp;

    @ApiModelProperty("JWT的ID")
    @Column(name="jti")
    private String jti;

    @ApiModelProperty("用户名称")
    @Column(name="username")
    private String username;

    @ApiModelProperty("用户拥有的权限")
    @Transient
    private List<String> authorities;
}
