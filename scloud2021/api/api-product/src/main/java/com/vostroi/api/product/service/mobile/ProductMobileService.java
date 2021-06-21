package com.vostroi.api.product.service.mobile;

import com.vostroi.api.product.beans.Product;
import com.vostroi.components.service.BaseService;

/**
 * @author Administrator
 * @date 2021/5/15 17:20
 * @projectName scloud2021
 * @title: ProductMobileService
 * @description: 商品 手机端
 */
public interface ProductMobileService extends BaseService<Product,Long> {

    /**
     * 测试 Hystrix 方法正确执行
     * @param id
     * @return
     */
    public Product hystrixRightMethod(Long id);

    /**
     * 测试 hystrix 方法执行异常
     * @param id
     * @return
     */
    public Product hystrixErrorMethod(Long id);

}
