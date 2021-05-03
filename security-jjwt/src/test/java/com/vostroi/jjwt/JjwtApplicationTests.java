package com.vostroi.jjwt;

import com.vostroi.jjwt.common.Constants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Decoder;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class JjwtApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成JWT
     */
    @Test
    void createJwt() {
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject("James")
                .setIssuedAt(new Date())
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET)
                // 设置失效时间 过期后解析后
                .setExpiration(new Date(System.currentTimeMillis()+60*10))
                ;
        // 签发生成token
        String token = builder.compact();

        // token格式:header.payload.signature
        System.out.println("token="+token);
        String[] split = token.split("\\.");
        System.out.println("split="+split.length);
        for (String s : split) {
            System.out.println(Base64Codec.BASE64.decodeToString(s));
        }
    }

    /**
     * 生成JWT ， 添加自定义声明
     */
    @Test
    void customJwt() {
        String[] roles = {"MANAGER", "NORMAL_USER", "ADMIN"};
        String[] authorities = {"READ","EXPORT","DOWNLOAD"};
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject("James")
                .setIssuedAt(new Date())
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET)
                // 设置失效时间 过期后解析后
                .setExpiration(new Date(System.currentTimeMillis()+60*10*120))
                .claim("username", "老王")
                .claim("password", "protected")
                .claim("roles", roles)
                .claim("authorities", authorities)
                ;
        // 签发生成token
        String token = builder.compact();

        // token格式:header.payload.signature
        System.out.println("token="+token);


        Claims claims = (Claims) Jwts.parser().setSigningKey(Constants.JWT_SECRET).parse(token).getBody();
        System.out.println("claims="+claims);
    }

    /**
     * 解析JWT
     */
    @Test
    void parseJwt() {
        // claims 就是在payload中声明的对象
        Claims claims = (Claims) Jwts.parser().setSigningKey(
                Constants.JWT_SECRET
        ).parse(
//                Constants.JWT_TOKEN_CUSTOM
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyLmtYvor5XmianlsZUiOiLlg4_kuKrlgrvlrZDlpJrkuYjlpb0iLCJ1c2VyX25hbWUiOiJBRE1JTiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2MjAxMDI1MzEsImF1dGhvcml0aWVzIjpbIlJFQUQiLCJERUxFVEUiLCJST0xFX0FETUlOIl0sImp0aSI6IjU4ZTYyZWE3LTc4OTItNGU5MS1hYmJhLWEwMDMyNDQ2ZTcxMyIsImNsaWVudF9pZCI6IlZPU1RST0kifQ.dP4rf6zp6BDmMSpGJx5bvMB8desBobb0_EaREinEUEA"
        ).getBody();
        System.out.println("claims="+claims);
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
    }

}
