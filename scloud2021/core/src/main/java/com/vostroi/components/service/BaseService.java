package com.vostroi.components.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.vostroi.components.dao.BaseDao;
import com.vostroi.components.entity.BaseEntity;
import com.vostroi.util.spring.SpringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/5/15 18:16
 * @projectName scloud2021
 * @title: BaseService
 * @description: service 公共接口
 */
public interface BaseService<T extends BaseEntity, ID extends Serializable> {
    /**
     * 获取 对象DAO，子类实现
     * @return
     */
    public BaseDao<T , ID> getDao();

    /**
     * 获取redis对象，子类实现
     * @return
     */
//    public RedisUtil getRedisUtil();

    /**
     * 根据ID获取对象
     * @param id
     * @return
     */
    public default T get(ID id){
        T t = getDao().select(id);
        return t==null?null:t;
    }

    /**
     * 根据ID获取对象 使用缓存
     * @param id
     * @return
     */
    public default T getUseCache(ID id){
        // 利用getRedisUtil从缓存中获取....
        // to do
        return null;
    }

    /**
     * 保存对象
     * 更新整个对象
     * @param t
     * @return 小于0 失败
     */
    public default T save(T t){
        if(t == null){
            return null;
        }
        if(null == t.getId()){
            // 新增
            if(t.getCrtTime()==null){
                t.setCrtTime(DateUtil.date());
                t.setLastUpdtTime(t.getCrtTime());
            }
            getDao().insert(t);
        } else if(t.getId()<0){
            return null;
        } else {
            // 修改
            t.setLastUpdtTime(DateUtil.date());
            getDao().update(t);
        }
        return getDao().select((ID)t.getId());
    }

    /**
     * 更新对象指定的一个属性
     * @param id
     * @param property
     * @param value
     * @return
     */
    public default T setProperty(ID id, String property, Object value){
        if(null == id){
            return null;
        }
        T t = get(id);
        if(t==null){
            return  null;
        }
        t = SpringUtils.setBeanProperty(t, property, value);
        t.setLastUpdtTime(DateUtil.date());
        int i = getDao().update(t);
        return i==1?t:null;
    }

    /**
     * 更新对象指定的部分属性
     * @param id
     * @param properties
     * @return
     */
    public default T setProperties(ID id ,  Map<String , Object> properties){
        if(null == id){
            return null;
        }
        T t = get(id);
        if(t==null){
            return  null;
        }
        t = SpringUtils.setBeanProperties(t, properties);
        t.setLastUpdtTime(DateUtil.date());
        int i = getDao().update(t);
        return i==1?t:null;
    }

}
