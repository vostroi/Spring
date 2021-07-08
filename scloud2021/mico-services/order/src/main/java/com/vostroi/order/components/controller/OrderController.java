package com.vostroi.order.components.controller;

import com.vostroi.order.components.amqp.listener.CustomerMessageListener;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/7/7 23:32
 * @projectName scloud2021
 * @title: OrderController
 * @description: TODO
 */
@Slf4j
@RestController
@RequestMapping(value = "/ord")
public class OrderController {
    @Value(value="${server.port}")
    private String serverPort;

    @Autowired
    private CustomerMessageListener custListener;

    @GetMapping(value = "/conn")
    public ResultData<String> connect(){
        log.info("custListener={}", custListener);
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000 , serverPort);
    }

}
