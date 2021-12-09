package com.whiplash.elastic.index;

import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2021/12/7 11:42
 * @projectName whiplash
 * @title: BaseIndex
 * @description: elastic search  索引基础字段
 * FieldType.Keyword 不分词
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseIndex implements Serializable {

    @org.springframework.data.annotation.Id
    private Long id;

    /**
     * 数据版本号
     */
    @Field(type = FieldType.Keyword)
    private int version = 0;

    /**
     * 标记数据状态 若使用到其它状态 请增加
     */
    @Field(type = FieldType.Keyword)
    private EnumConstant.RECORD_STATE state = EnumConstant.RECORD_STATE.VALID;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Long)
    private Long crtTime;

    /**
     * 最后修改时间
     */
    @Field(type = FieldType.Long)
    private Long lastUpdtTime;

    /**
     * 创建者
     */
    @Field(type = FieldType.Long)
    private Long crtUser;

    /**
     * 最后修改者
     */
    @Field(type = FieldType.Long)
    private Long lastUpdtUser;

    /**
     * 序号
     */
    @Field(type = FieldType.Long)
    private Long orderNum=0L;

    /**
     * 备注
     */
    @Field(type = FieldType.Text , analyzer = "ik_max_word" , searchAnalyzer = "ik_smart")
    private String remark= CommomConstant.STR_EMPTY;

}
