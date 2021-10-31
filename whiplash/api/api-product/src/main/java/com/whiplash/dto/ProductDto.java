package com.whiplash.dto;

import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstantProduct;
import com.whiplash.core.platform.bean.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/10/28 17:21
 * @projectName whiplash
 * @title: ProductDto
 * @description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductDto extends BaseDto {

    /**
     * 商品名称
     */
    private String skuName = CommomConstant.STR_EMPTY;

    /**
     * 商品品牌
     */
    private Long brand;

    private String brandName;

    /**
     * 一级分类
     */
    private Long firstCate;

    private String firstCateName;

    /**
     * 二级分类
     */
    private Long secondCate;

    private String secondCateName;

    /**
     * 三级分类
     */
    private Long thirdCate;

    private String thirdCateName;

    /**
     * 商品状态
     */
    private EnumConstantProduct.PRODUCT_STATUS status = EnumConstantProduct.PRODUCT_STATUS.DRAFT;

    private String statusName = status.getName();

    /**
     * 封面图地址
     */
    private String coverImg = CommomConstant.STR_EMPTY;

    /**
     * 轮播图
     */
    private List<String> imgList = new ArrayList<>();

    /**
     * 视频
     */
    private List<String> videoList = new ArrayList<>();

    /**
     * 详情描述 HTML
     */
    private String skuDesc = CommomConstant.STR_EMPTY;

    /**
     * 所属商户
     */
    private Long merchantId ;

    private String merchantName ;

    /**
     * 所属门店
     */
    private Long storeId ;

    private String storeName;

    /**
     * 销量
     */
    private BigInteger sales = BigInteger.ZERO;

    /**
     * 规格列表
     */
    private List<ProductSpecs> specs;
}
