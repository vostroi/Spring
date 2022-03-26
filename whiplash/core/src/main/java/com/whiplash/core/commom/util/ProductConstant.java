package com.whiplash.core.commom.util;

/**
 * @author Administrator
 * @date 2021/9/2 14:48
 * @projectName whiplash
 * @title: ProductConstant
 * @description: Product 常量类
 */
public  interface ProductConstant {

    // 通用常量

    final String PRODUCT_SPEC_NOT_FOR_SALE = "商品或规格不可售";

    final String PRODUCT_SPEC_FROZEN_EXCEPTION = "商品规格库存冻结异常";


    // 商品 相关常量  开始

    final String PRODUCT_NOT_EXIST_OR_NOT_ON = "商品不存在或未上架";

    // 商品 相关常量  结束




    // 商品规格 相关常量 开始

    final String SPECS_NOT_EXIST_OR_NOT_ENOUGH = "商品规格不存在或库存不足";

    final String SPECS_NOT_ON = "商品规格未上架";

    final String SPECS_NOT_ENOUGH = "商品规格库存不足";

    final String SPECS_FROZEN_STOCK_SUCCESS = "商品规格锁定库存成功";

    final String SPECS_FROZEN_STOCK_FAIL = "商品规格锁定库存失败";

    // 商品规格 相关常量  结束





}
