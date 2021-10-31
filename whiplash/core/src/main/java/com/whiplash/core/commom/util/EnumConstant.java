package com.whiplash.core.commom.util;

import lombok.Getter;

/**
 * @author Administrator
 * @date 2021/9/2 15:01
 * @projectName whiplash
 * @title: EnumConstant
 * @description: 系统使用的通用枚举值 ； 枚举类型的构造器 只能使用private 修饰
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
            return CommomConstant.STR_EMPTY;
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
     * 性别
     */
    public enum GENDER {
        /**
         * 未知
         */
        UNKNOWN("未知" , 0) ,

        /**
         * 男
         */
        MALE("男" , 1),

        /**
         * 女
         */
        FEMALE("女" , 2) ;

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

        GENDER(String name, int value){
            this.name = name;
            this.value = value;
        }

        public static String getName(int value){
            for(GENDER item : GENDER.values()){
                if(item.getValue() == value){
                    return item.getName();
                }
            }
            return CommomConstant.STR_EMPTY;
        }

        public static GENDER locateEnum(int value){
            for(GENDER item : GENDER.values()){
                if(item.getValue() == value){
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * 会员级别
     */
    public enum MEMBER_LEVEL {
        /**
         * 普通
         */
        NORMAL("普通" , 0) ,

        /**
         * VIP
         */
        MALE("VIP" , 1)

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

        MEMBER_LEVEL(String name, int value){
            this.name = name;
            this.value = value;
        }

        public static String getName(int value){
            for(MEMBER_LEVEL item : MEMBER_LEVEL.values()){
                if(item.getValue() == value){
                    return item.getName();
                }
            }
            return CommomConstant.STR_EMPTY;
        }

        public static MEMBER_LEVEL locateEnum(int value){
            for(MEMBER_LEVEL item : MEMBER_LEVEL.values()){
                if(item.getValue() == value){
                    return item;
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
        ER_3333_2222("服务忙" , "3333_2222"),

        HTTP_STATUS_SUCCESS("请求成功","200"),
        HTTP_STATUS_FAILED("请求失败","500"),
        HTTP_STATUS_UNAUTHORIZED("请求未授权","401"),
        HTTP_STATUS_FORBIDDEN("请求无权限，被拒绝","403"),

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
            return CommomConstant.STR_EMPTY;
        }
    }

    /**
     * 终端设备分类
     */
    public enum TERMINAL{
        /**
         * 电脑 1开头
         */
        PC("电脑" , 0) ,
        /**
         * 手机2开头
         */
        MOBILE("手机" , 1),
        /**
         * 手机 安卓
         */
        MBL_ANDTROID("安卓手机" , 2),
        /**
         * 手机 苹果
         */
        MBL_IOS("苹果手机" , 3),
        /**
         * 平板 3开头
         */
        PAD("平板" , 4),
        /**
         * 平板 安卓
         */
        PAD_ANDROID("安卓平板" , 4),
        /**
         * 平板 3开头
         */
        PAD_IOS("苹果平板" , 5)
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
            return CommomConstant.STR_EMPTY;
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
