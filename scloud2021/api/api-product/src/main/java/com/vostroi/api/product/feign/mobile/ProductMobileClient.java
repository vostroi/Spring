package com.vostroi.api.product.feign.mobile;

import com.vostroi.util.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
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
@FeignClient(value = "service-product" , fallbackFactory = ProductMobileClientFallbackFactory.class )
//@FeignClient(value = "service-product" , fallback = ProductMobileClientFallback.class)
@RequestMapping(value = "/prd/mbl")
public interface ProductMobileClient /*extends BaseFeignClient*/ {

    @GetMapping(value = "/dtl/{skuId}")
    ResultData<String> getSkuDetail(@PathVariable("skuId") Long skuId);

    @GetMapping(value = "/dtl/timeout/{skuId}")
    ResultData<String> getSkuDetailTimeout(@PathVariable("skuId") Long skuId);

    /**
     * 测试 Hystrix 正常执行
     * @param skuId
     * @return
     */
    @GetMapping(value = "/dtl/hystrix/right/{skuId}")
    ResultData<String> getSkuDetailHystrixRight(@PathVariable("skuId") Long skuId);

    /**
     * 测试 Hystrix 执行 超时/出错
     * @param skuId
     * @return
     */
    @GetMapping(value = "/dtl/hystrix/error/{skuId}")
    ResultData<String> getSkuDetailHystrixError(@PathVariable("skuId") Long skuId);

}
