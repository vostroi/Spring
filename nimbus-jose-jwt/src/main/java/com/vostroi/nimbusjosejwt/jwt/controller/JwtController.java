package com.vostroi.nimbusjosejwt.jwt.controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.vostroi.nimbusjosejwt.jwt.bean.PayloadDto;
import com.vostroi.nimbusjosejwt.jwt.common.CommonResult;
import com.vostroi.nimbusjosejwt.jwt.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author Administrator
 * @date 2021/8/31 16:38
 * @projectName nimbus-jose-jwt
 * @title: JwtController
 * @description: TOKEN 令牌管理接口
 */
@RestController
@RequestMapping(value="/token")
public class JwtController {
    private static final String SECRET = "NIMBUSJWT";
    @Autowired private JwtTokenService tokenService;

    /**
     * 生成token 对称加密算法 （HMAC）
     * @return
     */
    @RequestMapping(value = "/gen/hmac" , method = RequestMethod.GET)
    public CommonResult generateTokenByHMAC() {
        try {
            PayloadDto payloadDto = tokenService.getDefaultPayloadDto();
            String token = tokenService.generateTokenByHMAC(JSONUtil.toJsonStr(payloadDto), SecureUtil.md5(SECRET));
            return CommonResult.success(token);
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return CommonResult.failed();
    }

    @RequestMapping(value = "/vrfy/hmac"  , method = RequestMethod.GET)
    public CommonResult verifyTokenByHMAC(String token){
        try {
            PayloadDto payloadDto = tokenService.verifyTokenByHMAC(token, SecureUtil.md5(SECRET));
            return CommonResult.success(payloadDto);
        } catch (JOSEException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    /**
     * 生成token 非对称加密算法
     * @return
     */
    @RequestMapping(value = "/gen/rsa" , method = RequestMethod.GET)
    public CommonResult generateTokenByRSA() {

        try {
            PayloadDto payloadDto = tokenService.getDefaultPayloadDto();
            String token = tokenService.generateTokenByRSA(JSONUtil.toJsonStr(payloadDto), tokenService.getDefaultRSAKey());
            return CommonResult.success(token);
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return CommonResult.failed();
    }

    @RequestMapping(value = "/vrfy/rsa" , method = RequestMethod.GET)
    public CommonResult verifyTokenByRSA(String token) {
        try {
            PayloadDto payloadDto = tokenService.verifyTokenByRSA(token, tokenService.getDefaultRSAKey());
            return CommonResult.success(payloadDto);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return CommonResult.failed();
    }

    /**
     * 获取rsa公钥
     * @return
     */
    @GetMapping(value = "/get/rsa/pkey")
    public CommonResult getRsaPublicKey() {
        RSAKey rsaKey = tokenService.getDefaultRSAKey();

        JWKSet jwkSet = new JWKSet(rsaKey);

        return CommonResult.success(jwkSet);
    }


}
