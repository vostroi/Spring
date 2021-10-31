package com.whiplash.core.platform.bean;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/9/2 13:56
 * @projectName whiplash
 * @title: BaseEntity
 * @description: 抽象 DTO
 *
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    @org.springframework.data.annotation.Id     // 用于支持非关系性数据库
    private Long id;

    /**
     * 标记数据状态 若使用到其它状态 请增加
     */
    private EnumConstant.RECORD_STATE state = EnumConstant.RECORD_STATE.VALID;

    private String stateStr = state.getName();

    /**
     * 公共方法  将 BaseEntity 转换为 BaseDto ； 所有匹配属性
     * @param baseEntity
     * @return
     */
    public static BaseDto fromBaseEntityFull(BaseEntity baseEntity) {
        if(baseEntity == null){
            return null;
        }

        return JSONUtil.toBean(JSONUtil.toJsonStr(baseEntity), BaseDto.class);
    }

    /**
     * 公共方法  将 BaseEntity 转换为 BaseDto ； 所有匹配属性
     * @param baseEntity
     * @return
     */
    public static <T> T fromBaseEntityFull(BaseEntity baseEntity, Class<T> clzss) {
        if(baseEntity == null){
            return null;
        }

        return JSONUtil.toBean(JSONUtil.toJsonStr(baseEntity), clzss);
    }

//    public static <T> T toBean(String jsonString, Class<T> beanClass) {
//        return toBean(parseObj(jsonString), beanClass);
//    }


}
