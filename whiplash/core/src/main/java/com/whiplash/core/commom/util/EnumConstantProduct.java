package com.whiplash.core.commom.util;

import lombok.Getter;

/**
 * @author Administrator
 * @date 2021/9/5 17:09
 * @projectName whiplash
 * @title: EnumConstantProduct
 * @description: Product相关枚举
 */
public class EnumConstantProduct {

    /**
     * 商品状态
     */
    public enum PRODUCT_STATUS {

        /**
         * 已关闭
         */
        OFF("已下架", -0),

        /**
         * 已上架
         */
        ON("已上架", 1),

        /**
         * 草稿
         */
        DRAFT("草稿", 2),

        /**
         * 提交审核
         */
        AUDITING("提交审核", 3),

        /**
         * 审核驳回
         */
        AUDIT_REJECT("审核驳回", 4),


        /**
         * 审核通过
         */
        AUDIT_PASSED("提交审核", 5),

        ;

        @Getter
        private String name;

        @Getter
        private int value;

        PRODUCT_STATUS(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public static String getName(int value){
            for(EnumConstantProduct.PRODUCT_STATUS item : EnumConstantProduct.PRODUCT_STATUS.values()){
                if(item.getValue() == value){
                    return item.getName();
                }
            }
            return CommomConstant.STR_EMPTY;
        }

        public static EnumConstantProduct.PRODUCT_STATUS locateEnum(int value){
            for(EnumConstantProduct.PRODUCT_STATUS item : EnumConstantProduct.PRODUCT_STATUS.values()){
                if(item.getValue() == value){
                    return item;
                }
            }
            return null;
        }


    }

}
