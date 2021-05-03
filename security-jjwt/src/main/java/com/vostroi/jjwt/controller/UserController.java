package com.vostroi.jjwt.controller;

import com.vostroi.jjwt.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.Base64Codec;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 * @date 2021/5/3 0:20
 * @projectName security-oauth2
 * @title: UserController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    /**
     * 获取当前登录的用户
     * @param authentication
     * @return
     */
    @GetMapping(value = "/curt")
    public Object getCurrentUser(Authentication authentication) {
        // 实际是 MyUserDetailsServiceImpl 中的封装的用户
        Object principal = authentication.getPrincipal();
        return principal;
    }

    /**
     * 测试连接，权限是否有效
     * 需要访问权限，需要token
     * @param p
     * @return
     */
    @RequestMapping(value = "conn")
    public Object tstConnect(String p){
        return p + System.currentTimeMillis();
    }

    /**
     * 解析 JWT TOKEN
     * @param request
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/cnvt" , method = {RequestMethod.GET , RequestMethod.POST})
    public Object parseJwtToken(HttpServletRequest request, Authentication authentication) {
        String basic = request.getHeader("Authorization");
        log.info("JWT TOKEN BASIC={}", basic);

        String token = basic.substring(basic.indexOf("bearer") + 7);
        Claims claims = (Claims) Jwts.parser()
                //  要getBytes，直接使用String的密钥，报密钥不匹配错误
                // .setSigningKey(Constants.JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                // 与上面同效果
                .setSigningKey(Base64Codec.BASE64.encode(Constants.JWT_SECRET))
                .parse(token).getBody();
        return claims;
    }

}
