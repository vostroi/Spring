package com.vostroi.nimbusjosejwt.jwt.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.vostroi.nimbusjosejwt.jwt.bean.PayloadDto;

import java.text.ParseException;

/**
 * @author Administrator
 * @date 2021/8/31 13:41
 * @projectName nimbus-jose-jwt
 * @title: JwtTokenService
 * @description: TODO
 */
public interface JwtTokenService {

    /**
     * 对称加密算法 生成token
     * @param payloadStr
     * @param secret
     * @return
     */
    public String generateTokenByHMAC(String payloadStr , String secret) throws JOSEException;

    /**
     * 对称加密算法 解密token
     * @param token
     * @param secret
     * @return
     */
    public PayloadDto verifyTokenByHMAC(String token , String secret) throws JOSEException, ParseException;

    /**
     * 非对称加密算法 生成token
     * @param payloadStr
     * @param rsakey
     * @return
     */
    public String generateTokenByRSA(String payloadStr , RSAKey rsakey) throws JOSEException;

    /**
     * 非对称算法 解密token
     * @param token
     * @param rsaKey
     * @return
     */
    public PayloadDto verifyTokenByRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException;

    /**
     * 获取默认的 PayloadDto
     * @return
     */
    public PayloadDto getDefaultPayloadDto();

    /**
     * 获取默认的 RSAKEY
     * @return
     */
    public RSAKey getDefaultRSAKey();


}
