package com.vostroi.util.spring;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @author Administrator
 * @date 2021/5/15 19:28
 * @projectName scloud2021
 * @title: SpringUtils
 * @description: TODO
 */
public class SpringUtils {


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
        //com.alibaba.fastjson.JSONObject json = FastJsonUtil.getJsonFromString(FastJsonUtil.toJson(t));
        //json.put(property, value);
    	//json.toJavaObject(t.getClass());
//        @SuppressWarnings("unchecked")
//        Class<T> cls = (Class<T>) t.getClass();
//        return FastJsonUtil.toBean(json.toJSONString(), cls);

        JSONObject jsonObject = JSONUtil.parseObj(t, false);
        jsonObject.set(property, value);
        Class<T> cls = (Class<T>) t.getClass();
        return jsonObject.toBean(cls);
    }

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
}
