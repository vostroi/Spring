package com.vostroi.customer.components.controller.mobile;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vostroi.api.feign.order.OrderMobileClient;
import com.vostroi.customer.fallback.SentinelFallback;
import com.vostroi.customer.handler.SentinelHandler;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @date 2021/7/19 23:23
 * @projectName scloud2021
 * @title: SentinelController
 * @description: 测试 sentinel
 */
@RestController
@RequestMapping(value="/sntnl")
public class SentinelController {
    @Autowired
    private OrderMobileClient orderMobileClient;

    @GetMapping(value = "/tsta")
    public ResultData<String> testA(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testA");
    }


    @GetMapping(value = "/tstb")
    public ResultData<String> testB(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testB");
    }

    @GetMapping(value = "/tstc")
    @SentinelResource(blockHandler = "testDHandler")
    public ResultData<String> testC(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testC");
    }


    /**
     * 测试  sentinel 热点限流
     * @return
     */
    @GetMapping(value = "/tstd")
    @SentinelResource(value = "tstd" , blockHandler = "testDHandler")
    public ResultData<String> testD(
            @RequestParam(name = "skuid" , required = false) Long skuid,
            @RequestParam(name = "id" , required = false) Long id
    ){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testD");
    }

    // 不能是private
    public ResultData<String> testDHandler(Long skuid, Long id, BlockException e)
    {
        return ResultData.getResultData(EnumConstant.RESULT_CODE.FA_2222 , "testDHandler");
    }

    /**
     * 使用合局的 blockHandler 解耦业务代码，减少重复代码
     * @param skuid
     * @param id
     * @return
     */
    @GetMapping(value = "/tste")
    @SentinelResource(value = "tste" ,  blockHandlerClass = SentinelHandler.class, blockHandler = "blockHandler1",
            fallbackClass = SentinelFallback.class , fallback = "fallback1"
        )
    public ResultData testE(
                                @RequestParam(name = "skuid" , required = false) Long skuid,
                                @RequestParam(name = "id" , required = false) Long id
                                )
    {
        long l = 10L / skuid;
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testE");
    }

    /**
     * 测试 sentinel + openfeign
     * @param key
     * @return
     */
    @GetMapping(value="/feigh/conn/{key}")
    public ResultData sentinelFeignConn(@PathVariable("key") String key) {
        ResultData<String> resultData = orderMobileClient.connect();
        return resultData;
    }

}
