package com.whiplash.core.platform.controller;

import com.whiplash.core.commom.util.ParamDateEditor;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.platform.bean.BaseEntity;
import com.whiplash.core.platform.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/9/3 16:26
 * @projectName whiplash
 * @title: BaseController
 * @description: 抽象基础controller， 提取公共API接口
 * 注意：所有get方法，都要转换成对应的 DTO 避免暴露过多字段
 */
@Slf4j
@RestController
@CrossOrigin("*")
public abstract class BaseController<T extends BaseEntity , ID extends Serializable> {

    /**
     * 子类实现
     * @return
     */
    public abstract BaseService<T , ID> getService();

    /**
     *根据ID获取对象 从数据库
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public ResultData<T> get(@PathVariable("id") ID id) {
        T t = getService().get(id);
        return ResultData.getResultData(t);
    }

    @GetMapping(value = "/get/cache/{id}")
    public ResultData<T> getByIdUseCache(@PathVariable("id") ID id) {
        // 先从缓存中获取

        T t = getService().getUseCache(id);
        return ResultData.getResultData(t);
    }

    /**
     * 新增/修改
     * 注意：传入的对象的所有属性都会更新到数据库
     * @param t
     * @return
     */
    @RequestMapping(value = {"/crt","/mdf"} , method = {RequestMethod.POST,RequestMethod.PUT})
    public ResultData<T> createOrModify(@RequestBody T t) {
        if (t == null) {
            return ResultData.getResultData(t);
        }

        t = getService().save(t);

        return ResultData.getResultData(t);
    }

    /**
     * 批量 新增/修改
     * 注意：传入的对象的所有属性都会更新到数据库
     * @param tList
     * @return
     */
    @RequestMapping(value = {"/btcrt","/btmdf"} , method = {RequestMethod.POST,RequestMethod.PUT})
    public ResultData<List<T>> createOrModifyBatch(@RequestBody List<T> tList) {
        if (tList == null || tList.isEmpty()) {
            return ResultData.getResultDataParamEmpty();
        }
         return ResultData.getResultData(getService().saveBatch(tList) );
    }

    /**
     * 修改指定对象的某个属性值
     * @param id
     * @param property
     * @param value
     * @return
     */
    @RequestMapping(value = "/set/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResultData<T> setProperty(@PathVariable("id") ID id, @RequestParam(value = "prop", required = true) String property,
                                     @RequestParam(value = "value", required = true) Object value) {
        T t = getService().setProperty(id, property, value);
        return ResultData.getResultData(t);
    }

    /**
     * 修改指定对象的多个属性
     * @param id
     * @param properties
     * @return
     */
    @RequestMapping(value = "/multiset/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResultData<Object> setProperties(@PathVariable("id") ID id, @RequestBody Map<String, Object> properties) {
        if (properties == null || properties.isEmpty()) {
            return ResultData.getResultDataParamEmpty();
        }

        T t = getService().setProperties(id, properties);
        return ResultData.getResultData(t);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}" , method = {RequestMethod.DELETE})
    public ResultData delete(@PathVariable("id") ID id) {
        getService().delete(id);
        return ResultData.getResultDataSuccess();
    }

    /**
     * 根据List<ID> 批量删除
     * @param idList
     * @return
     */
    @RequestMapping(value = "/btdel" , method = {RequestMethod.DELETE})
    public ResultData deleteBatch(@RequestParam("idList") List<ID> idList) {
        if (idList == null || idList.isEmpty()) {
            return ResultData.getResultDataParamEmpty();
        }

        getService().delete(idList);

        return ResultData.getResultDataSuccess();
    }

    /**
     * 注册绑定器
     */
    @InitBinder
    public void initControllerBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        // String类型的参数会先trim
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        // 字符串类型的日期 绑定到日期类型
        binder.registerCustomEditor(Date.class, new ParamDateEditor());
    }


}
