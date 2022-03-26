package com.vostroi.product.components.service.impl.mobile;

import com.vostroi.api.components.beans.Product;
import com.vostroi.api.service.mobile.ProductMobileService;
import com.vostroi.components.dao.BaseDao;
import com.vostroi.product.components.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2021/5/15 18:11
 * @projectName scloud2021
 * @title: ProductMobileServiceImpl
 * @description: TODO
 */
@Service
@Slf4j
public class ProductMobileServiceImpl implements ProductMobileService {
    @Autowired private ProductDao dao;

    @Override
    public BaseDao<Product, Long> getDao() {
        return dao;
    }

    @Override
    public Product hystrixRightMethod(Long id) {
        log.info("hystrixRightMethod 线程池：{},id={}", Thread.currentThread().getName(),id);
        return getDao().select(id);
    }

    @Override
    public Product hystrixErrorMethod(Long id) {
        // 模拟超时
        try {
            TimeUnit.SECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("hystrixErrorMethod 线程池：{},id={}", Thread.currentThread().getName(),id);
        return getDao().select(id);
    }
}
