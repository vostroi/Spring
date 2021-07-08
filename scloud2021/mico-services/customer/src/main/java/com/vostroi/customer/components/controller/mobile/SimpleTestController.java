package com.vostroi.customer.components.controller.mobile;

import cn.hutool.core.util.StrUtil;
import com.vostroi.api.customer.service.mq.SimpleMessageProvider;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@RefreshScope
@RestController
@RequestMapping(value = "/spl")
public class SimpleTestController {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTestController.class);
    /**
     * application.yml中配置的参数，测试动态修改，动态加载
     */
    @Value(value = "${application.property.dynamic.read}")
    private String dynamic;

    @Autowired
    private SimpleMessageProvider msgProvider;


    @GetMapping(value = "/dynamic")
    public ResultData<String> dynamicProperty(){
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000,dynamic);
    }

    /**
     * 测试 spring cloud stream发送消息到 rabbitmq
     * @return
     */
    @GetMapping(value = "/strm")
    public ResultData send2Mq(){
        String seria = StrUtil.uuid();
        String send = msgProvider.send(seria);
        logger.debug("send msg send={},seria={}",send,seria);
        log.debug("send msg send={},seria={}",send,seria);
        log.info("send msg send={},seria={}",send,seria);
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000, send+"----"+seria);
    }

}
