package com.vostroi.product.components.service.impl;

import com.vostroi.api.product.beans.Product;
import com.vostroi.api.product.service.ProductMobileService;
import com.vostroi.components.dao.BaseDao;
import com.vostroi.product.components.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2021/5/15 18:11
 * @projectName scloud2021
 * @title: ProductMobileServiceImpl
 * @description: TODO
 */
@Service
public class ProductMobileServiceImpl implements ProductMobileService {
    @Autowired
    private ProductDao productDao;

    @Override
    public BaseDao<Product, Long> getDao() {
        return productDao;
    }

}
