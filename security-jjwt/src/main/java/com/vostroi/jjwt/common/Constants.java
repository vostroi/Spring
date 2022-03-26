package com.vostroi.jjwt.common;

/**
 * @author Administrator
 * @date 2021/5/3 16:59
 * @projectName jjwt
 * @title: Constants
 * @description: 常量配置
 */
public class Constants {

    /**
     * 签名密钥
     * 可以设置成中文，但需要在使用时 Constants.JWT_SECRET.getBytes(StandardCharsets.UTF_8)
     */
    public static final String JWT_SECRET="JWT_SECRET";

    public static final String JWT_TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2MTZkODMzYS1iMjBhLTQ5YmUtOTdjOS0xODUwMTE3MjY5MzIiLCJzdWIiOiJKYW1lcyIsImlhdCI6MTYyMDAzMjQzMH0.H2zY6RnUD3HZoFMOBMxcPSmNjeQ8gkzGhXuodwgy6vI";
    public static final String JWT_TOKEN_CUSTOM="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NWQ3YWI4Yy1kMTFiLTQ1NTUtODA2MC1hM2EzYmJmNDlmMDYiLCJzdWIiOiJKYW1lcyIsImlhdCI6MTYyMDAzNDI0MCwiZXhwIjoxNjIwMDM0MzEyLCJ1c2VybmFtZSI6IuiAgeeOiyIsInBhc3N3b3JkIjoicHJvdGVjdGVkIiwicm9sZXMiOlsiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiQURNSU4iXSwiYXV0aG9yaXRpZXMiOlsiUkVBRCIsIkVYUE9SVCIsIkRPV05MT0FEIl19.F0UgQXHRhiSGkPSuBeyp6cIPzAQwtXixZDBLmSMp1s4";
    public static final String JWT_TOKEN_EXPIRED="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwOTdjYWE1YS05ZjhkLTRiYjctOWI2ZS0wMjVhOWQ4ZjFhYjAiLCJzdWIiOiJKYW1lcyIsImlhdCI6MTYyMDAzMzY1OCwiZXhwIjoxNjIwMDMzNjU5fQ.uT__JJmNt47IY8YCIS7cgnrVNIkW318tcjT8mEciniM";
	
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

    /**
     * oauth2授权模式-产生刷新令牌
     */
    public static final String AUTHORIZATION_REFRESH_TOKEN="refresh_token";

}
