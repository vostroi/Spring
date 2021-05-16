package com.vostroi.customer.components.controller.mobile;

import com.vostroi.api.customer.beans.Customer;
import com.vostroi.api.customer.service.mobile.CustomerMobileService;
import com.vostroi.api.product.beans.Product;
import com.vostroi.components.controller.BaseController;
import com.vostroi.components.service.BaseService;
import com.vostroi.util.MicroServiceName;
import com.vostroi.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/5/15 15:37
 * @projectName scloud2021
 * @title: CustomerMobileController
 * @description: 手机端用户服务API
 * 注意：extends BaseController<Customer , Long> 必须带上泛型，不然spring mvc会将请求中的id转成String
 */
@Slf4j
@RestController
@RequestMapping(value = "/cust/mbl")
public class CustomerMobileController extends BaseController<Customer, Long> {
    @Autowired private CustomerMobileService service;

    @Override
    public BaseService getService() {
        return service;
    }

    @GetMapping(value = "/prd/{skuId}")
    public ResultData<Product> listProduct(@PathVariable("skuId") Long skuId){
        return this.getRestTemplate().getForObject(MicroServiceName.MICRO_SERVER_PRODUCT + "/prd/mbl/get/" + skuId, ResultData.class);
    }

    @GetMapping(value = "/prd/dtl/{skuId}")
    public ResultData<Product> productDetail(@PathVariable("skuId") Long skuId){
        return this.getRestTemplate().getForObject(MicroServiceName.MICRO_SERVER_PRODUCT + "/prd/mbl/dtl/" + skuId, ResultData.class);
    }



}
