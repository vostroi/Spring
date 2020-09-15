package com.vostroi.components.controller;

import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;

/**
 * @author Administrator
 * @date 2020/7/29 16:32
 * @projectName mcloud
 * @title: BaseController
 * @description: TODO
 */
public class BaseController {

    /**
     * 服务熔断 通用返回数据
     * @return
     */
    public ResultData requestError() {
        return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_1111, "请求出错");
    }
}
