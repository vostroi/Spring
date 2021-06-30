package com.vostroi.customer.components.controller.mobile;

import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/6/29 22:48
 * @projectName scloud2021
 * @title: SimpleTestController
 * @description: 简单的测试
 */
@RefreshScope
@RestController
@RequestMapping(value = "/spl")
public class SimpleTestController {

    /**
     * application.yml中配置的参数，测试动态修改，动态加载
     */
    @Value(value = "${application.property.dynamic.read}")
    private String dynamic;

    @GetMapping(value = "/dynamic")
    public ResultData<String> dynamicProperty(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000,dynamic);
    }


}
