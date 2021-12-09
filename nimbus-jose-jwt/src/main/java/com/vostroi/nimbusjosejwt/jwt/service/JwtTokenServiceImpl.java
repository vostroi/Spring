package com.vostroi.nimbusjosejwt.jwt.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.vostroi.nimbusjosejwt.jwt.bean.PayloadDto;
import com.vostroi.nimbusjosejwt.jwt.exception.JwtExpiredException;
import com.vostroi.nimbusjosejwt.jwt.exception.JwtInvalidException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2021/8/31 13:21
 * @projectName nimbus-jose-jwt
 * @title: JwtTokenServiceImpl
 * @description: JWT业务类，根据HMAC算法生成和解析JWT令牌
 */
@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    @Override
    public String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException {
        // 创建JWS头 设置签名 和 类型
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();

        Payload payload = new Payload(payloadStr);

        // JWS对象
        JWSObject jwsObject = new JWSObject(header, payload);

        // HMAC签名器
        JWSSigner signer = new MACSigner(secret);

        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    @Override
    public PayloadDto verifyTokenByHMAC(String token, String secret) throws JOSEException, ParseException {
        JWSObject jwsObject = JWSObject.parse(token);

        JWSVerifier jwsVerifier = new MACVerifier(secret);

        if (!jwsObject.verify(jwsVerifier)) {
            throw new JwtInvalidException("token签名不合法，可能被篡改");
        }

        String payload = jwsObject.getPayload().toString();

        PayloadDto payloadDto = JSONUtil.toBean(payload, PayloadDto.class);

        if (payloadDto.getExp() < System.currentTimeMillis()) {
            throw new JwtExpiredException("token已过期");
        }

        return payloadDto;
    }

    @Override
    public String generateTokenByRSA(String payloadStr, RSAKey rsakey) throws JOSEException {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build();

        Payload payload = new Payload(payloadStr);

        JWSObject jwsObject = new JWSObject(header, payload);

        // RSA签名器
        JWSSigner signer = new RSASSASigner(rsakey);

        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    @Override
    public PayloadDto verifyTokenByRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException {

        JWSObject jwsObject = JWSObject.parse(token);

        // 公钥
        RSAKey rsaPublicKey = rsaKey.toPublicJWK();

        // 使用分解创建验证器
        RSASSAVerifier rsaverifier = new RSASSAVerifier(rsaPublicKey);

        if(!jwsObject.verify(rsaverifier)){
            throw new JwtInvalidException("token签名不合法，可能被篡改");
        }

        String payload = jwsObject.getPayload().toString();

        PayloadDto payloadDto = JSONUtil.toBean(payload, PayloadDto.class);

        if (payloadDto.getExp() < System.currentTimeMillis()) {
            throw new JwtExpiredException("token已过期");
        }

        return payloadDto;
    }

    @Override
    public PayloadDto getDefaultPayloadDto() {

        // 默认过期时间 5分钟
        Date exp = DateUtil.offsetSecond(new Date(), 60 * 5);

        return PayloadDto.builder()
                .sub("JWT-LEARN")
                .iat(System.currentTimeMillis())
                .exp(exp.getTime())
                .jti(UUID.randomUUID().toString())
                .username("张哥卖保险")
                .authorities(CollUtil.toList("TENANT", "MERCHANT"))
                .build();
    }

    @Override
    public RSAKey getDefaultRSAKey() {

        // 从classpath下获取 rsa密钥
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());

        // 密钥对
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    }
}
