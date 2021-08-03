package com.vostroi.api.feign.product.mobile;

import com.vostroi.api.components.beans.Product;
import com.vostroi.api.components.fallback.ProductMobileClientFallback;
import com.vostroi.api.components.fallback.ProductMobileClientFallbackFactory;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

/**
 * @author Administrator
 * @date 2021/6/16 23:41
 * @projectName scloud2021
 * @title: ProductMobileServiceFeign
 * @description: 提供给消费者服务调用；其中的方法与 controller 方法一一对应（可以直接将方法名copy过来） ， 让消费方调用更简单
 * value 服务提供方 服务名
 * fallbackFactory 处理服务降级 （必须让 spring 扫描到 ， 让 @EnableFeignClients(basePackages = {"com.vostroi.api.feign"}) 扫描到不起作用）
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

    /**
     * 测试链路跟踪 customer-->product-->order
     * @return
     */
    @GetMapping(value = "/sleuth")
    ResultData<String> sleuthTrace();


    @PostMapping(value = "/stprc/{skuId}/{price}")
    public ResultData<String> setPrice(@PathVariable("skuId") Long skuId , @PathVariable("price") BigDecimal price);
}
