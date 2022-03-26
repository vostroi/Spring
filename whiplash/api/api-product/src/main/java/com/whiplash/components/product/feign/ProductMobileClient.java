package com.whiplash.components.product.feign;

import com.whiplash.components.product.bean.Product;
import com.whiplash.core.commom.util.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @date 2021/10/26 16:57
 * @projectName whiplash
 * @title: ProductMobileClient
 * @description:
 */
@FeignClient(value = "product-server" /*, fallbackFactory =*/ )
@RequestMapping(value = "/prod/mbl")
public interface ProductMobileClient {

    @GetMapping(value = "/get/{id}")
    public ResultData<Product> getById(@PathVariable("id") Long id) ;

    @GetMapping(value = "/get/cache/{id}")
    public ResultData<Product> getByIdUseCache(@PathVariable("id") Long id) ;

}
