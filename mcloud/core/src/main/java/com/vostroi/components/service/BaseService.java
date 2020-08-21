package com.vostroi.components.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.vostroi.components.entity.BaseEntity;
import com.vostroi.components.entity.IdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

/**
 * @author Administrator
 * @date 2020/8/9 13:09
 * @projectName mcloud
 * @title: BaseService
 * @description: TODO
 */
public interface BaseService <T extends BaseEntity , ID extends String>{

    /**
     * 返回业务模型 DAO
     * @return
     */
    public JpaRepository<T , ID > getRepositry();

    /**
     * 保存业务对象
     * @param t
     * @return
     */
    @Modifying
    public default T save(T t){
        if(t == null){
            return null;
        }
        if(StrUtil.isBlank(t.getId())){
            t.setId(StrUtil.uuid());
        }
        if(!Optional.ofNullable(t.getCrtTime()).isPresent()){
            t.setCrtTime(DateUtil.date());
        }
        t.setLstUpdtTime(DateUtil.date());
        return getRepositry().save(t);
    }
}
