package com.vostroi.product.components.controller.mobile;

import cn.hutool.json.JSONUtil;
import com.vostroi.api.product.beans.Product;
import com.vostroi.api.product.service.mobile.ProductMobileService;
import com.vostroi.components.controller.BaseController;
import com.vostroi.components.service.BaseService;
import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2021/5/15 15:37
 * @projectName scloud2021
 * @title: ProductMobileController
 * @description: 手机端商品服务API
 * 注意：extends BaseController<Product , Long> 必须带上泛型，不然spring mvc会将请求中的id转成String
 */
@Slf4j
@RestController
@RequestMapping(value = "/prd/mbl")
public class ProductMobileController extends BaseController<Product , Long> {
    @Value("${server.port}") private int serverPort;
    @Autowired private ProductMobileService service;

    @Override
    public BaseService getService() {
        return service;
    }
	
    /**
     * @param skuId
     * @return 根据skuId获取商品详情
     */
    @GetMapping(value = "/dtl/{skuId}")
    public ResultData<String> getSkuDetail(@PathVariable("skuId") Long skuId){
        Product product = service.get(skuId);
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000, "商品数据：" + JSONUtil.toJsonStr(product) + "端口：" + serverPort);
    }

    /**
     * 测试服务商超时
     * @param skuId
     * @return
     */
    @GetMapping(value = "/dtl/timeout/{skuId}")
    public ResultData<String> getSkuDetailTimeout(@PathVariable("skuId") Long skuId){
        Product product = service.get(skuId);
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultData.getResultData(EnumConstant.RESULT_CODE.SU_0000, "商品数据：" + JSONUtil.toJsonStr(product) + "端口：" + serverPort);
    }

}
