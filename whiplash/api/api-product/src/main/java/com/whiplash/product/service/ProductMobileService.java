package com.whiplash.product.service;

import com.whiplash.components.product.bean.Product;
import com.whiplash.core.platform.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2021/10/26 15:45
 * @projectName whiplash
 * @title: ProductMobileService
 * @description: TODO
 */
@Service
public interface ProductMobileService extends BaseService<Product, Long> {

    /**
     * 带锁获取商品 根据ID
     * @param id
     * @return
     */
    public Product getByIdLock(Long id);

}
