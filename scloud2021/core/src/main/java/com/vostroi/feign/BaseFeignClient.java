package com.vostroi.feign;

import com.vostroi.components.entity.BaseEntity;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/6/16 23:52
 * @projectName scloud2021
 * @title: BaseFeign
 * @description: 这里 一一对应 BaseController 的方法
 */
//@FeignClient
public interface BaseFeignClient<T extends BaseEntity, ID extends Serializable> {
    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}")
    public ResultData<T> get(@PathVariable("id") ID id);

    /**
     * 只能有一个映射
     * @param t
     * @return
     */
    @PostMapping(value = "/crt")
    //@PostMapping(value = {"/crt","/mdf"})
    public ResultData<T> create(@RequestBody T t);

    @PostMapping(value = "/mdf")
    public ResultData<T> modify(@RequestBody T t);

    @PostMapping("/multiset/{id}")
    public ResultData<T> setProperties(@PathVariable(value = "id") ID id, @RequestBody Map<String, Object> properties);
}
