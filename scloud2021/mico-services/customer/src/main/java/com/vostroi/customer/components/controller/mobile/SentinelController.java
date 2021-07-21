package com.vostroi.customer.components.controller.mobile;

import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/7/19 23:23
 * @projectName scloud2021
 * @title: SentinelController
 * @description: 测试 sentinel
 */
@RestController
@RequestMapping(value="/" +
        "")
public class SentinelController {

    @GetMapping(value = "/tsta")
    public ResultData<String> testA(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testA");
    }


    @GetMapping(value = "/tstb")
    public ResultData<String> testB(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testB");
    }

    @GetMapping(value = "/tstc")
    public ResultData<String> testC(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , "testC");
    }
}
