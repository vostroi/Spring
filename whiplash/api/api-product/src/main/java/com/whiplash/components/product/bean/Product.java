package com.whiplash.components.product.bean;

import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstantProduct;
import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author Administrator
 * @date 2021/10/25 17:40
 * @projectName whiplash
 * @title: Product
 * @description: 商品表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "t_product")
public class Product extends BaseEntity {


    /**
     * 商品名称
     */
    @Column(name = "sku_name" , columnDefinition = "varchar(256)")
    private String skuName = CommomConstant.STR_EMPTY;

    /**
     * 商品品牌
     */
    @Column(name = "brand" , columnDefinition = "bigint")
    private Long brand;

    /**
     * 一级分类
     */
    @Column(name = "first_cate" , columnDefinition = "bigint")
    private Long firstCate;

    /**
     * 二级分类
     */
    @Column(name = "secod_cate" , columnDefinition = "bigint")
    private Long secondCate;

    /**
     * 三级分类
     */
    @Column(name = "third_cate" , columnDefinition = "bigint")
    private Long thirdCate;

    /**
     * 商品状态
     */
    @Column(name = "status" , columnDefinition = "int")
    private EnumConstantProduct.PRODUCT_STATUS status = EnumConstantProduct.PRODUCT_STATUS.DRAFT;

    /**
     * 封面图地址
     */
    @Column(name = "cover_img" , columnDefinition = "varchar(128)")
    private String coverImg = CommomConstant.STR_EMPTY;

    /**
     * 详情描述 HTML
     */
    @Column(name = "sku_desc" , columnDefinition = "text")
    private String skuDesc = CommomConstant.STR_EMPTY;

    /**
     * 所属商户
     */
    @Column(name = "merchant_id" , columnDefinition = "bigint")
    private Long merchantId ;

    /**
     * 所属门店
     */
    @Column(name = "store_id" , columnDefinition = "bigint")
    private Long storeId ;

    /**
     * 销量
     */
    @Column(name = "sales" , columnDefinition = "bigint")
    private BigInteger sales = BigInteger.ZERO;

}
