package com.whiplash.core.commom.util;

import cn.hutool.core.util.RandomUtil;

/**
 * @author Administrator
 * @date 2021/10/25 17:45
 * @projectName whiplash
 * @title: OrderUtil
 * @description: 订单服务 订单编号 生成规则
 */
public class OrderCodeUtil {

    /**
     * 生成  订单编号
     * @return
     */
    public static Long generateOrderCode(){
        return RandomUtil.randomLong(100000000L , 999999999999L);
    }


}
