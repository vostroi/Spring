package com.whiplash.components.product.feign;

import com.whiplash.components.order.dto.SingleOrderSubmitDto;
import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.core.commom.spring.PagerUtil;
import com.whiplash.core.commom.util.CommonResult;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.dto.ProductSpecsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/10/26 17:13
 * @projectName whiplash
 * @title: ProductSpecsMobileClient
 * @description: TODO
 */
@FeignClient(value = "product-server")
@RequestMapping(value = "/spec/mbl")
public interface ProductSpecsMobileClient {

    @GetMapping(value = "/get/{id}")
    public ResultData<ProductSpecs> getById(@PathVariable("id") Long id) ;

    @GetMapping(value = "/get/cache/{id}")
    public ResultData<ProductSpecs> getByIdUseCache(@PathVariable("id") Long id) ;

    @PostMapping(value = "/ordered/stock")
    public ResultData<Boolean> orderedStock(@RequestBody SingleOrderSubmitDto sosd);

    @GetMapping(value="/list/prod/{prodId}")
    public ResultData<List<ProductSpecsDto>> listAllSpecByProductId(@PathVariable("prodId") Long productId) ;

    @GetMapping(value = "/page/{page}/{size}")
    public ResultData<List<ProductSpecs>> listByPage(@PathVariable("page") int page, @PathVariable("size") int size) ;

}
