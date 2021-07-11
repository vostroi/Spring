package com.vostroi.api.components.fallback;

import com.vostroi.api.feign.product.mobile.ProductMobileClient;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2021/6/23 0:10
 * @projectName scloud2021
 * @title: ProductMobileClientFallback
 * @description: 统一处理服务降级
 * 配合  ProductMobileClient 一起使用，统一处理服务降级
 * 方式1 实现 FallbackFactory 接口 ， 同时 FeignClient 配置 @FeignClient(value = "service-product" , fallbackFactory = ProductMobileClientFallbackFactory.class )
 */
@Component
@Slf4j
public class ProductMobileClientFallbackFactory implements FallbackFactory<ProductMobileClient> {
    @Override
    public ProductMobileClient create(Throwable throwable) {
        return new ProductMobileClient() {
            @Override
            public ResultData<String> getSkuDetail(Long skuId) {
                return ProductMobileClientFallbackFactory.fallback();
            }

            @Override
            public ResultData<String> getSkuDetailTimeout(Long skuId) {
                return ProductMobileClientFallbackFactory.fallback();
            }

            @Override
            public ResultData<String> getSkuDetailHystrixRight(Long skuId) {
                return ProductMobileClientFallbackFactory.fallback();
            }

            @Override
            public ResultData<String> getSkuDetailHystrixError(Long skuId) {
                return ProductMobileClientFallbackFactory.fallback();
            }

            @Override
            public ResultData<String> sleuthTrace() {
                return ProductMobileClientFallbackFactory.fallback();
            }
        };
    }

    private static ResultData fallback (){
        log.info("timeOutHandler Thread={}", Thread.currentThread().getName());
        return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_2222 , EnumConstant.RESULT_CODE.ER_3333_2222.getCode());
    }
}
