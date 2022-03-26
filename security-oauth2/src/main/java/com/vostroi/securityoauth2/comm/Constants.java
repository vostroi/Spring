package com.vostroi.securityoauth2.comm;

/**
 * @author Administrator
 * @date 2021/4/24 22:40
 * @projectName security
 * @title: Constants
 * @description: 常量类
 */
public class Constants {

    /**
     * 模拟数据库中存在的用户的用户名
     */
    public static final String USER_NAME_ADMIN="ADMIN";
    /**
     * 明文密码
     */
    public static final String USER_NAME_ADMIN_PASSWORD="123456";
    /**
     * 加密后的正确密码
     */
    public static final String USER_NAME_ADMIN_PASSWORD_ENCODED="$2a$10$PbGl31kv7pshf78n8wVTrOq5V4uCyFbYZqa7W5Nb.sBb0/NukMPbq";

    public static final String CLIENT_ID="VOSTROI";
    public static final String CLIENT_SECRET="$2a$10$cWm.3VPHdsvcWwYGG4JYsuievgdvLabu2ubpMkaoyh.BURwAzCCve";
    /**
     * oauth2授权模式-授权码模式
     */
    public static final String AUTHORIZATION_CODE="authorization_code";




}
