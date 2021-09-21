package com.whiplash.core.platform.dao;

import com.whiplash.core.commom.util.EnumConstant;
import com.whiplash.core.platform.bean.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/2 16:12
 * @projectName whiplash
 * @title: BaseDao
 * @description: 抽象基础DAO 提取公共方法
 * JpaSpecificationExecutor 支持动态查询
 */
public interface BaseDao<T extends BaseEntity , ID extends Serializable> extends PagingAndSortingRepository<T,ID> , JpaSpecificationExecutor<T> {

    /**
     * 根据 state 查询全部
     * @param state
     * @return
     */
    List<T> findByState(EnumConstant.RECORD_STATE state);

    /**
     * 根据 state 分页查询
     * @param state
     * @param pager
     * @return
     */
    Page<T> findByState(EnumConstant.RECORD_STATE state, Pageable pager);

    /**
     * 根据 id 列表查询全部
     * @param idList
     * @return
     */
    List<T> findByIdIn(List<ID> idList);

    /**
     * 根据 id 列表  分页查询
     * @param idList
     * @param pager
     * @return
     */
    Page<T> findByIdIn(List<ID> idList , Pageable pager);

    /**
     * 等值查询 唯一索引
     * @param property
     * @param value
     * @return
     */
    default T getByPropertyEqual(String property, Object value){
        return null;
    }

    // 动态查询封装






}
