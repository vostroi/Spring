package com.vostroi.customer.fallback;

import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @date 2021/7/26 22:18
 * @projectName scloud2021
 * @title: SentinelFallback
 * @description: 主要处理 运行时异常 情况
 *
 */
public class SentinelFallback {

    /**
     * 静态方法
     * @param skuid
     * @param id
     * @return
     */
    public static ResultData fallback1( Long skuid,Long id , Throwable e )
    {
        return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_1111 , "fallback1");
    }
}
