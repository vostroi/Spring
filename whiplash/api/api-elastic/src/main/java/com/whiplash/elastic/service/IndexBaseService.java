package com.whiplash.elastic.service;

import com.whiplash.elastic.index.BaseIndex;
import com.whiplash.elastic.index.ProductSpecs;
import com.whiplash.elastic.repository.IndexBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/12/8 11:42
 * @projectName whiplash
 * @title: IndexBaseService
 * @description: 抽象 ES 索引业务 基础 API
 */
public interface IndexBaseService<T extends BaseIndex , ID extends Serializable> {

    /**
     * 获取 索引 repository 子类 实现
     * @return
     */
    public IndexBaseRepository<T,ID> getRepository();

    /**
     * 获取 ElasticsearchRestTemplate 子类实现
     * @return
     */
    public ElasticsearchRestTemplate getElasticSearchRestTemplate();

    /**
     * 搜索
     *
     * @param key
     * @return
     */
    public Page<T> search(String key, Pageable pager);

    /**
     * 聚合 搜索
     * @param key
     * @param pager
     * @return
     */
    public Page<T> searchAggregation(String key, Pageable pager);

    /**
     * 根据 索引 ID 获取
     * @param id
     * @return
     */
    default T getById(ID id){
        if (id == null) {
            return null;
        }

        return getRepository().findById(id).orElse(null);
    }

    /**
     * 新增  更新 索引
     * @param t
     * @return
     */
    default boolean save(T t){
        if (t == null || t.getId() == null) {
            return false;
        }

        getRepository().save(t);

        return true;
    }

    /**
     * 批量 新增 更新 索引
     * 量太大不适用
     * @param tList
     * @return
     */
    default boolean saveBatch(List<T> tList) {
        if (tList == null || tList.isEmpty()) {
            return false;
        }

        Iterable<T> ts = getRepository().saveAll(tList);
        if (ts == null || ts.iterator() == null || !ts.iterator().hasNext()) {
            return false;
        }
        return true;
    }

    default boolean delete(ID id) {
        if (id == null) {
            return false;
        }

        getRepository().deleteById(id);

        return true;
    }

    default boolean delete(List<ID> idList) {
        if (idList == null || idList.isEmpty()) {
            return false;
        }

        idList.forEach(id->{
            delete(id);
        });

        return true;
    }

    default boolean deleteBatch(List<T> tList) {
        if (tList == null || tList.isEmpty()) {
            return false;
        }
        getRepository().deleteAll(tList);

        return true;
    }

    /**
     * 创建索引
     * 索引实体 默认会自动创建索引
     * @param clzz
     * @return
     */
    default boolean createIndex(Class<T> clzz){
        boolean exists = getElasticSearchRestTemplate().indexOps(clzz).exists();
        if (exists) {
            return true;
        }

        return getElasticSearchRestTemplate().indexOps(clzz).create();
    }

    default boolean deleteIndex(Class<T> clzz) {
        boolean exists = getElasticSearchRestTemplate().indexOps(clzz).exists();
        if (!exists) {
            return true;
        }

        return getElasticSearchRestTemplate().indexOps(clzz).delete();
    }


}


