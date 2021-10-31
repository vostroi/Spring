package com.whiplash.product.components.dao;

import com.whiplash.components.product.bean.Product;
import com.whiplash.core.platform.dao.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

/**
 * @author Administrator
 * @date 2021/9/5 18:15
 * @projectName whiplash
 * @title: CustomerDao
 * @description: TODO
 */
@Repository
public interface ProductMobileDao extends BaseDao<Product, Long> {


    /**
     * 带锁 根据ID 查询
     * @param id
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT p FROM Product p WHERE p.id = :id")
    Product findByIdLock(@Param(("id")) Long id);




}
