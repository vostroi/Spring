package com.whiplash.oauth.components.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/19 14:17
 * @projectName whiplash
 * @title: OpenApiController
 * @description: 暴露接口 获取RSA公钥
 */
@RestController
@RequestMapping(value = "/api")
public class OpenApiController {
    @Autowired KeyPair keyPair;

    @GetMapping(value = "/rsa/publickey")
    public Map<String , Object> getPublicKey(){
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey rsaKey = new RSAKey.Builder(rsaPublicKey).build();
        return new JWKSet(rsaKey).toJSONObject();
    }



}
