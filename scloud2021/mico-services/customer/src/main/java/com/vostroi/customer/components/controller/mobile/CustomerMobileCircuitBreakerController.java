package com.vostroi.customer.components.controller.mobile;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vostroi.api.components.beans.Customer;
import com.vostroi.api.feign.product.mobile.ProductMobileClient;
import com.vostroi.api.service.mobile.CustomerMobileService;
import com.vostroi.components.controller.BaseController;
import com.vostroi.components.service.BaseService;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/5/15 15:37
 * @projectName scloud2021
 * @title: CustomerMobileController
 * @description: 测试熔断
 */
@Slf4j
@RestController
@RequestMapping(value = "/cust/mbl/circuit")
public class CustomerMobileCircuitBreakerController extends BaseController<Customer, Long> {
    @Autowired private CustomerMobileService service;
    @Autowired private ProductMobileClient productMobileClient;
    @Override
    public BaseService getService() {
        return service;
    }


    /**
     * 正常调用服务
     * @param skuId
     * @return
     */
    @GetMapping(value="/hystrix/fei/prd/dtl/{skuId}")
    @HystrixCommand
    public ResultData<String> productDetailHystrix(@PathVariable("skuId") Long skuId){
        return productMobileClient.getSkuDetailHystrixRight(skuId);
    }

    // 服务熔断测试 START
    /**
     * 测试熔断，没调用服务
     * @param skuId
     * @return
     */
    @GetMapping(value="/hystrix/breaker/noFeign/prd/dtl/{skuId}")
    @HystrixCommand
    public ResultData<String> productDetailHystrixCircuitBreakerNoFeign(@PathVariable("skuId") Long skuId){
        long div = 100 / skuId;
        String id = IdUtil.fastUUID();
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000,id);
    }

    @GetMapping(value="/hystrix/breaker/prd/dtl/{skuId}")
    @HystrixCommand
    public ResultData<String> productDetailHystrixCircuitBreaker(@PathVariable("skuId") Long skuId){
        return productMobileClient.getSkuDetailHystrixError(skuId);
    }
    // 服务熔断测试 END

}
