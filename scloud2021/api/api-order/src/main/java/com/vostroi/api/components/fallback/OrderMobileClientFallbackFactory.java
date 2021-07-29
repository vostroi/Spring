package com.vostroi.api.components.fallback;

import com.vostroi.api.feign.order.OrderMobileClient;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2021/7/26 23:02
 * @projectName scloud2021
 * @title: OrderMobileClientFallback
 * @description: OrderMobileClientFallback implements OrderMobileClient 会出错， 使用FallbackFactory
 */
@Component
@Slf4j
public class OrderMobileClientFallbackFactory implements FallbackFactory<OrderMobileClient> {


    private static ResultData fallback (){
        log.info("OrderMobileClientFallback fallback={}", Thread.currentThread().getName());
        return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_2222 , EnumConstant.RESULT_CODE.ER_3333_2222.getCode());
    }

    @Override
    public OrderMobileClient create(Throwable cause) {
        return new OrderMobileClient() {
            @Override
            public ResultData<String> connect() {
                return fallback();
            }
        };
    }
}
