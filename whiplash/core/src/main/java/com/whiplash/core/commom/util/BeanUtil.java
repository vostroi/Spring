package com.whiplash.core.commom.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/2 15:01
 * @projectName whiplash
 * @title: BeanUtil
 * @description: 实体工具类
 */
public class BeanUtil {

    /**
     * bean 设置多字段值
     * @param t
     * @param properties
     * @param <T>
     * @return
     */
    public static <T> T setBeanProperties(T t, Map<String,Object> properties){
        if(t==null){
            return null;
        }
        if(properties == null || properties.isEmpty()){
            return t;
        }
        JSONObject jsonObject = JSONUtil.parseObj(t, false);
        jsonObject.putAll(properties);
        Class<T> cls = (Class<T>) t.getClass();
        return jsonObject.toBean(cls);
    }

    /**
     * bean 设置指定的属性值
     * @param t
     * @param property
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T setBeanProperty(T t,String property,Object value) {
        if(t==null){
            return null;
        }
        if(StrUtil.isBlank(property)){
            return t;
        }

        JSONObject jsonObject = JSONUtil.parseObj(t, false);
        jsonObject.set(property, value);
        Class<T> cls = (Class<T>) t.getClass();
        return jsonObject.toBean(cls);
    }
}
