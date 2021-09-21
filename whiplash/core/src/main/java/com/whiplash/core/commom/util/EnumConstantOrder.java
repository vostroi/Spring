package com.whiplash.core.commom.util;

import lombok.Getter;

/**
 * @author Administrator
 * @date 2021/9/5 17:09
 * @projectName whiplash
 * @title: EnumConstantOrder
 * @description: order相关枚举
 */
public class EnumConstantOrder {

    /**
     * 订单状态
     */
    public enum ORDER_STATUS {

        /**
         * 已关闭
         */
        CLOSED("已关闭", -20),

        /**
         * 已取消
         */
        CANCEL("已取消", -10),

        /**
         * 待付款
         */
        NOT_PAYED("待付款", 0),

        /**
         * 已付款
         */
        PAYED("已付款", 10),

        /**
         * 未发货
         */
        NOT_DELIVER("未发货", 20),

        /**
         * 已发货
         */
        DELIVERED("已发货", 30),

        // 30-60 预留

        /**
         * 确认收货
         */
        CONFIRMED("确认收货",60),

        // 70 以后 预留给售后状态

        /**
         * 已退款
         */
        REFUNDED("已退款", 70)
        ;

        @Getter
        private String name;

        @Getter
        private int value;

        ORDER_STATUS(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public static String getName(int value){
            for(EnumConstantOrder.ORDER_STATUS item : EnumConstantOrder.ORDER_STATUS.values()){
                if(item.getValue() == value){
                    return item.getName();
                }
            }
            return CommomConstant.STR_EMPTY;
        }

        public static EnumConstantOrder.ORDER_STATUS locateEnum(int value){
            for(EnumConstantOrder.ORDER_STATUS item : EnumConstantOrder.ORDER_STATUS.values()){
                if(item.getValue() == value){
                    return item;
                }
            }
            return null;
        }
    }

}
