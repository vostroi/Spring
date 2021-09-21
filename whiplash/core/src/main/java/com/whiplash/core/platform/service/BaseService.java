package com.whiplash.core.platform.service;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.whiplash.core.commom.exception.PropertiesSetException;
import com.whiplash.core.commom.util.BeanUtil;
import com.whiplash.core.platform.bean.BaseEntity;
import com.whiplash.core.platform.dao.BaseDao;
import org.springframework.data.jpa.repository.Modifying;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 * @date 2021/9/3 15:47
 * @projectName whiplash
 * @title: BaseService
 * @description: 抽象基础service 提取公共方法
 */
public interface BaseService<T extends BaseEntity , ID extends Serializable> {

    /**
     * 获取 dao  子类实现
     * @return
     */
    public BaseDao<T, ID> getDao();

    // 获取 redisUtil

    /**
     * 根据ID获取对象
     * @param id
     * @return T
     */
    public default T get(ID id){
        if (id == null) {
            return null;
        }

        Optional<T> t = getDao().findById(id);
        return t.orElse(null);
    }

    /**
     * 根据ID获取对象 使用缓存 子类实现
     * @param id
     * @return
     */
    public T getUseCache(ID id) ;

    /**
     * 保存（新增/修改）整个对象 ， t 的所有属性都会保存
     * 没有ID生成策略 使用MYSQL自增 后期增加ID生成策略
     * 需要鉴权
     * @param t
     * @return T
     */
    @Modifying
    public default  T save(T t){
        if(t==null){
            return null;
        }

        // 新增
        if (t.getId() == null) {
            if (t.getCrtTime() == null || t.getCrtTime().after(DateUtil.date())) {
                t.setCrtTime(DateUtil.date());
                t.setLastUpdtTime(t.getCrtTime());
            }
        }
        // 修改
        else{
            t.setLastUpdtTime(DateUtil.date());
        }
        return getDao().save(t);
    }

    /**
     * 批量保存（新增/修改）整个对象 ， t 的所有属性都会保存
     * 没有ID生成策略 使用MYSQL自增 后期增加ID生成策略
     * 需要鉴权
     * @param tList
     * @return
     */
    @Modifying
    public default List<T> saveBatch(List<T> tList){
        if(tList == null || tList.isEmpty()){
            return null;
        }

        tList.forEach(t->{
            if (t.getId() != null) {
                if(t.getCrtTime()==null){
                    T tDB = get((ID) t.getId());
                    t.setCrtTime(tDB.getCrtTime());
                }
                t.setLastUpdtTime(DateUtil.date());
            }
        });

        Iterable<T> ts = getDao().saveAll(tList);
        if(ts == null){
            return null;
        }
        return ListUtil.toList(ts);
    }

    /**
     * 更新指定对象的某个属性值
     * 需要鉴权
     * @param id
     * @param property
     * @param value
     * @return
     */
    @Modifying
    public default T setProperty(ID id, String property, Object value){
        if (id == null) {
            return null;
        }

        T t = get(id);
        if (t == null) {
            return null;
        }

        t = BeanUtil.setBeanProperty(t, property, value);
        if(t==null){
            throw new PropertiesSetException("属性设置异常");
        }

        return save(t);
    }

    /**
     * 更新指定对象的多个属性值
     * 需要鉴权
     * @param id
     * @param properties
     * @return
     */
    @Modifying
    public default T setProperties(ID id, Map<String, Object> properties) {
        if (id == null) {
            return null;
        }

        T t = get(id);
        if (t == null) {
            return null;
        }

        t = BeanUtil.setBeanProperties(t, properties);
        if(t==null){
            throw new PropertiesSetException("属性设置异常");
        }

        return save(t);
    }

    /**
     * 删除指定对象
     * 需要鉴权
     * @param id
     * @return
     */
    @Modifying
    public default void delete(ID id) {
        if (id == null) {
            return ;
        }

        getDao().deleteById(id);
    }

    /**
     * 删除指定对象
     * 需要鉴权
     * @param t
     */
    public default void delete(T t) {
        if (t == null) {
            return;
        }

        getDao().delete(t);
    }

    /**
     * 批量删除
     * 需要鉴权
     * @param idList
     */
    @Modifying
    public default void delete(List<ID> idList) {
        if (idList == null || idList.isEmpty()) {
            return;
        }

        idList.forEach(id->{
            getDao().deleteById(id);
        });
    }

    /**
     * 批量删除
     * 需要鉴权
     * @param tList
     */
    @Modifying
    public default void deleteBatch(List<T> tList) {
        if (tList == null || tList.isEmpty()) {
            return;
        }

        tList.forEach(t->{
            getDao().delete(t);
        });
    }

}
