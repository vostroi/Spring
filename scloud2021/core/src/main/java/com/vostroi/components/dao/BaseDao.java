package com.vostroi.components.dao;

import com.vostroi.components.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 * @date 2021/5/15 17:05
 * @projectName scloud2021
 * @title: BaseDao
 * @description: DAO 接口 公共操作
 */
public interface BaseDao<T extends BaseEntity, ID extends java.io.Serializable> {

    /**
     * 基础插入操作
     * @param t
     * @return
     */
    public int insert(T t);

    /**
     * 基础更新操作
     * @param t
     * @return
     */
    public int update(T t);

    /**
     * 根据 id 查询
     * @return
     * @param id
     */
    public T select(@Param("id") ID id);


}
