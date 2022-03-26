package com.vostroi.sec.components.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/12/16 17:08
 * @projectName sec
 * @title: OpenController
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/open")
public class OpenController {

    @GetMapping(value = "/pro/get/{sku}")
    public String getProduct(@PathVariable("sku") String sku) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product desc sku=" + sku ;
    }

    @GetMapping(value = "/ord/get/{code}")
    public String getOrder(@PathVariable("code") String orderCode) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order desc code=" + orderCode;
    }


}
