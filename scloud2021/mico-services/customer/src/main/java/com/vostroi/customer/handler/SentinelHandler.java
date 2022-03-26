package com.vostroi.customer.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;

/**
 * @author Administrator
 * @date 2021/7/22 22:31
 * @projectName scloud2021
 * @title: SentinelHandler
 * @description:  主要处理 违反限流规则 情况
 * 全局处理 sentinel blockhandler ；  可以针对不同业务 定义相应的方法
 * 要使用 自定义的 blockHandler ， 需要 @SentinelResource 指定资源名， 控制台 配置也要以 @SentinelResource 的资源名配置， 以url配置不起效
 * blockHandler 的方法必须是静态的
 * blockHandler 的方法必须与 @SentinelResource 注解的方法参数一致 加上 BlockException
 */
public class SentinelHandler {

    /**
     * 要static方法
     * @param e
     * @return
     */
    public static ResultData blockHandler1(Long skuid, Long id, BlockException e)
    {
        return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_2222);
    }

}
