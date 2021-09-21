package com.whiplash.core.commom.util;

import lombok.Getter;

/**
 * @author Administrator
 * @date 2021/9/5 17:09
 * @projectName whiplash
 * @title: EnumConstantOrder
 * @description: payment 相关枚举
 */
public class EnumConstantPayment {

    /**
     * 支付方式
     */
    public enum PAY_WAY {

        /**
         * 微信
         */
        WEIXIN("微信", 0),

        /**
         * 支付宝
         */
        ZHIFUBAO("支付宝", 10),

        /**
         * 京东支付
         */
        JD("京东", 20),

        /**
         * 银联
         */
        UNION("银联", 30)
        ;

        @Getter
        private String name;

        @Getter
        private int value;

        PAY_WAY(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public static String getName(int value){
            for(EnumConstantPayment.PAY_WAY item : EnumConstantPayment.PAY_WAY.values()){
                if(item.getValue() == value){
                    return item.getName();
                }
            }
            return CommomConstant.STR_EMPTY;
        }

        public static EnumConstantPayment.PAY_WAY locateEnum(int value){
            for(EnumConstantPayment.PAY_WAY item : EnumConstantPayment.PAY_WAY.values()){
                if(item.getValue() == value){
                    return item;
                }
            }
            return null;
        }
    }

}
