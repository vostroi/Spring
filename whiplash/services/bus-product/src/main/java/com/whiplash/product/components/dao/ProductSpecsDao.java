package com.whiplash.product.components.dao;

import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.core.platform.dao.BaseDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/10/26 15:40
 * @projectName whiplash
 * @title: ProductSpecsDao
 * @description:
 */
@Repository
public interface ProductSpecsDao extends BaseDao<ProductSpecs,Long> {

    List<ProductSpecs> findAllByProductId(Long productId, Sort sort);

    /**
     * Lock 作用同 for update一样 将此行数据进行加锁（有索引是行锁，否则是表锁？） 整个方法提交事务后再释放
     * Lock 不能用在nativeQuery=true
     * @param id
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT ps FROM ProductSpecs ps WHERE ps.id = :id" )
    ProductSpecs findByIdLock(@Param(("id")) Long id);

}
