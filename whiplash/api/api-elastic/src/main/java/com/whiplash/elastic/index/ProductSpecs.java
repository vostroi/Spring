package com.whiplash.elastic.index;

import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstantProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Administrator
 * @date 2021/12/7 11:37
 * @projectName whiplash
 * @title: ProductSpecs
 * @description: ElasticSearch 索引
 * @Document(indexName = "product_specs" , replicas = 0)
 *
 * replicas 过时了，默认是1 单机情况下， 索引是黄色 修改为0
 * 默认 createIndex = true 自动创建索引 手动创建设置为false
 *
 * 可以将 多个要搜索的字段 合并索引到 一个字段  来做分词搜索
 * 其它字段就精确匹配搜索
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(indexName = "product_specs" , replicas = 0 )
public class ProductSpecs extends BaseIndex {

    /**
     * 商品编号
     */
    @Field(type = FieldType.Long)
    private Long productId;

    /**
     * 品牌英文
     */
    @Field(type = FieldType.Keyword)
    private String brandEn;

    /**
     * 品牌中文
     */
    @Field(type = FieldType.Keyword)
    private String brandCh;

    /**
     * 一级分类名
     */
    @Field(type = FieldType.Keyword)
    private String firstCat;

    /**
     * 二级分类名
     */
    @Field(type = FieldType.Keyword)
    private String secondCat;

    /**
     * 三级分类名
     */
    @Field(type = FieldType.Keyword)
    private String thirdCat;

    @Field(type = FieldType.Text , analyzer = "ik_max_word" , searchAnalyzer = "ik_smart")
    private String productName;

    /**
     * 规格名称
     */
    @Field(type = FieldType.Text , analyzer = "ik_max_word" , searchAnalyzer = "ik_smart")
    private String specsName = CommomConstant.STR_EMPTY;

    /**
     * 规格状态
     */
    @Field(type = FieldType.Integer)
    private Integer status = EnumConstantProduct.PRODUCT_STATUS.DRAFT.getValue();

    @Field(type = FieldType.Double)
    private BigDecimal price=BigDecimal.ZERO;

    /**
     * 规格可用库存
     */
    @Field(type = FieldType.Long)
    private Integer stockLeft = 0;

    /**
     * 规格冻结库存
     */
    @Field(type = FieldType.Long)
    private Integer stockFrozen = 0;

    /**
     * 销量
     */
    @Field(type = FieldType.Long)
    private Integer sales = 0;

}
