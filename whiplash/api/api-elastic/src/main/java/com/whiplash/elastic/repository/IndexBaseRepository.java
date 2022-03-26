package com.whiplash.elastic.repository;

import com.whiplash.elastic.index.BaseIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/12/8 11:46
 * @projectName whiplash
 * @title: IndexBaseRepository
 * @description: 抽象 索引 repository
 * spring data 的 Repository 数据访问规范，只要是 Repository 的子类型，都不需要自己写代码，也不需要添加任何注解
 */
public interface IndexBaseRepository<T extends BaseIndex, ID extends Serializable> extends ElasticsearchRepository<T , ID> {

    /**
     * 根据 id 列表查询全部
     * @param idList
     * @return
     */
    List<T> findByIdIn(List<ID> idList);

}
