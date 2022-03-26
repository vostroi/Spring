package com.vostroi.product.components.dao;

import com.vostroi.api.components.beans.Product;
import com.vostroi.components.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2021/5/15 17:02
 * @projectName scloud2021
 * @title: ProductDao
 * @description: Product DAO 接口操作
 * 使用 mybatis , 推荐使用 @Mapper， 如果使用@Repository 有时插入可能会有问题
 * 不加@Component idea报错， 看到烦
 */
@Component
@Mapper
public interface ProductDao extends BaseDao<Product,Long> {


}
