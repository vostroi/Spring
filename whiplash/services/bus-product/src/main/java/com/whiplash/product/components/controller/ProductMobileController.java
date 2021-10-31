package com.whiplash.product.components.controller;

import com.whiplash.components.product.bean.Product;
import com.whiplash.core.platform.controller.BaseController;
import com.whiplash.core.platform.service.BaseService;
import com.whiplash.service.ProductMobileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/10/26 16:16
 * @projectName whiplash
 * @title: ProductMobileController
 * @description: TODO
 */
@Slf4j
@RestController
@RequestMapping(value = "/prod/mbl")
public class ProductMobileController extends BaseController<Product,Long> {
    @Autowired ProductMobileService prodMblService;

    @Override
    public BaseService<Product, Long> getService() {
        return prodMblService;
    }



}
