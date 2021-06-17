package com.vostroi.api.product.feign.mobile;

import com.vostroi.api.product.beans.Product;
import com.vostroi.feign.BaseFeignClient;
import com.vostroi.util.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @date 2021/6/16 23:41
 * @projectName scloud2021
 * @title: ProductMobileServiceFeign
 * @description: 提供给消费者服务调用；其中的方法与 controller 方法一一对应（可以直接将方法名copy过来） ， 让消费方调用更简单
 * value 服务提供方 服务名
 * fallbackFactory 处理服务降级
 */
@FeignClient(value = "service-product" /** , fallbackFactory = */ )
@RequestMapping(value = "/prd/mbl")
public interface ProductMobileClient /*extends BaseFeignClient*/ {

    @GetMapping(value = "/dtl/{skuId}")
    ResultData<String> getSkuDetail(@PathVariable("skuId") Long skuId);

    @GetMapping(value = "/dtl/timeout/{skuId}")
    public ResultData<String> getSkuDetailTimeout(@PathVariable("skuId") Long skuId);

}
