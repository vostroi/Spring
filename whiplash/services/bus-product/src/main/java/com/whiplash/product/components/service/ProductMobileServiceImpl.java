package com.whiplash.product.components.service;

import com.whiplash.components.product.bean.Product;
import com.whiplash.core.platform.dao.BaseDao;
import com.whiplash.product.components.dao.ProductMobileDao;
import com.whiplash.product.service.ProductMobileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @date 2021/9/5 18:18
 * @projectName whiplash
 * @title: CustomerServiceImpl
 * @description: TODO
 */
@Slf4j
@CacheConfig(cacheNames = "spring:cache:product")
@Service
public class ProductMobileServiceImpl implements ProductMobileService {
    @Autowired private ProductMobileDao prodDao;

    @Override
    public BaseDao getDao() {
        return prodDao;
    }

    @Cacheable()
    @Override
    public Product getUseCache(Long id) {
        // 先从缓存中获取

        return get(id);
    }

    @Transactional(rollbackFor = Exception.class , isolation = Isolation.READ_COMMITTED , propagation = Propagation.REQUIRED)
    @Override
    public Product getByIdLock(Long id) {
        return prodDao.findByIdLock(id);
    }

}
