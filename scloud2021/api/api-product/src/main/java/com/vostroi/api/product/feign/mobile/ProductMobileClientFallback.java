package com.vostroi.api.product.feign.mobile;

import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2021/6/23 0:24
 * @projectName scloud2021
 * @title: ProductMobileClientFallback
 * @description: 统一处理服务降级
 * 配合  ProductMobileClient 一起使用，统一处理服务降级
 * 方式2 实现 FeignClient 接口 , 同时 FeignClient 配置  @FeignClient(value = "service-product" , fallback = ProductMobileClientFallback.class)
 *
 */
//@Component //@Component 注释掉，不然 扫描注解 会与  ProductMobileClientFallbackFactory 冲突
@Slf4j
public class ProductMobileClientFallback implements ProductMobileClient {
    @Override
    public ResultData<String> getSkuDetail(Long skuId) {
        return fallback();
    }

    @Override
    public ResultData<String> getSkuDetailTimeout(Long skuId) {
        return fallback();
    }

    @Override
    public ResultData<String> getSkuDetailHystrixRight(Long skuId) {
        return fallback();
    }

    @Override
    public ResultData<String> getSkuDetailHystrixError(Long skuId) {
        return fallback();
    }

    private static ResultData fallback (){
        log.info("timeOutHandler Thread={}", Thread.currentThread().getName());
        return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_2222 , EnumConstant.RESULT_CODE.ER_3333_2222.getCode());
    }
}
