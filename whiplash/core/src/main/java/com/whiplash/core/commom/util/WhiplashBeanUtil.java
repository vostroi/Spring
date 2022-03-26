package com.whiplash.core.commom.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/2 15:01
 * @projectName whiplash
 * @title: BeanUtil
 * @description: 实体工具类
 */
public class WhiplashBeanUtil {

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

    /**
     * 将 source 转 为 target
     * @param source
     * @param target
     * @param <T>
     */
    public static <T> T toBean(ResultData<?> source, T target) {
        if (source == null) {
            return target;
        }

        if (source.getData() == null) {
            return target;
        }

        BeanUtils.copyProperties(source.getData(), target);

        return target;
    }

    public static <T> T toBean(ResultData<?> source, Class<T> clzz) {
        if (source == null || clzz == null) {
            return null;
        }

        if (source.getData() == null || source.getData() == null) {
            return null;
        }

        String str = JSONUtil.toJsonStr(source.getData());
        T t = JSONUtil.toBean(str, clzz);
        return t;
    }

    public static <T> T toBean(Object source, T target) {
        if (source == null || target == null) {
            return target;
        }

        BeanUtils.copyProperties(source, target);

        return target;
    }

    public static <T> T toBean(Object source, Class<T> clzz) {
        if (source == null || clzz == null) {
            return null;
        }

        String str = JSONUtil.toJsonStr(source);
        T t = JSONUtil.toBean(str, clzz);

        return t;
    }

    public static <T> List<T> toList(ResultData<List<Object>> source, Class<T> clzz) {
        if (source == null || clzz == null) {
            return null;
        }

        if (source.getData() == null || source.getData().isEmpty()) {
            return null;
        }

        String str = JSONUtil.toJsonStr(source.getData());
        T t = JSONUtil.toBean(str, clzz);

        JSONArray jsonArray = JSONUtil.parseArray(source);
        List<T> tList = JSONUtil.toList(jsonArray, clzz);

        return tList;
    }
}
