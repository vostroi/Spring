package com.vostroi.util;

import lombok.Data;
import lombok.Getter;

/**
 * @author Administrator
 * @title: EnumConstant
 * @projectName mcloud
 * @description: 系统使用的枚举值 ； 枚举类型的构造器 只能使用private 修饰
 * @date 2020/7/1716:40
 */
public class EnumConstant {

    /**
     * 数据记录状态枚举值
     */
    public enum RECORD_STATE{
        /**
         * 无效
         */
        INVALID("无效" , 0) ,
        /**
         * 有效
         */
        VALID("有效" , 1);

        /**
         * 中文描述
         */
        @Getter
        private String name;
        /**
         * 值 存入于数据库中
         */
        @Getter
        private int value;

        RECORD_STATE(String name, int value){
            this.name = name;
            this.value = value;
        }

        public static String getName(int value){
            for(RECORD_STATE rs : RECORD_STATE.values()){
                if(rs.getValue() == value){
                    return rs.getName();
                }
            }
            return "";
        }

        public static RECORD_STATE locateEnum(int value){
            for(RECORD_STATE rs : RECORD_STATE.values()){
                if(rs.getValue() == value){
                    return rs;
                }
            }
            return null;
        }
    }

    /**
     * API 接口 返回 代码
     */
    public enum RESULT_CODE{
        /**
         * 接口返回成功 没有任何错误
         */
        SU_0000("成功" , "0000_0000"),
        /**
         * 接口返回警告
         */
        WA_1111("警告" , "1111_0000"),
        /**
         * 接口返回失败 接口没能成功处理请求
         */
        FA_2222("失败" , "2222_0000"),
        /**
         * 接口返回错误 接口处理出错 出现在异常
         */
        ER_3333("错误" , "3333_0000"),
        /**
         * 服务熔断
         */
        ER_3333_1111("服务错误" , "3333_1111"),
        /**
         * 服务降级
         */
        ER_3333_2222("服务忙" , "3333_2222")
        ;

        @Getter
        private String msg;
        @Getter
        private String code;

        RESULT_CODE(String msg, String code){
            this.msg = msg;
            this.code = code;
        }

        public static String getName(String code){
            for (RESULT_CODE rc : RESULT_CODE.values()){
                if(rc.getCode().equals(code)){
                    return rc.getMsg();
                }
            }
            return "";
        }
    }

    /**
     * 终端设备分类
     */
    public enum TERMINAL{
        /**
         * 电脑 1开头
         */
        PC("电脑" , 1) ,
        /**
         * 手机2开头
         */
        MOBILE("手机" , 2),
        /**
         * 手机 安卓
         */
        MBL_ANDTROID("安卓手机" , 21),
        /**
         * 手机 苹果
         */
        MBL_IOS("苹果手机" , 22),
        /**
         * 平板 3开头
         */
        PAD("平板" , 3),
        /**
         * 平板 安卓
         */
        PAD_ANDROID("安卓平板" , 31),
        /**
         * 平板 3开头
         */
        PAD_IOS("苹果平板" , 32)
        ;

        /**
         * 中文描述
         */
        @Getter
        private String name;
        /**
         * 值 存入于数据库中
         */
        @Getter
        private int value;

        TERMINAL(String name, int value){
            this.name = name;
            this.value = value;
        }

        public static String getName(int value){
            for(TERMINAL t : TERMINAL.values()){
                if(t.getValue() == value){
                    return t.getName();
                }
            }
            return "";
        }

        public static TERMINAL locateEnum(int value){
            for(TERMINAL t : TERMINAL.values()){
                if(t.getValue() == value){
                    return t;
                }
            }
            return null;
        }
    }



}
