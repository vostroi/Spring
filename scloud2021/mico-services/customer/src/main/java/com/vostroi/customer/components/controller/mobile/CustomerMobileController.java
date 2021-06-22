package com.vostroi.customer.components.controller.mobile;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vostroi.api.customer.beans.Customer;
import com.vostroi.api.customer.service.mobile.CustomerMobileService;
import com.vostroi.api.product.beans.Product;
import com.vostroi.api.product.feign.mobile.ProductMobileClient;
import com.vostroi.components.controller.BaseController;
import com.vostroi.components.service.BaseService;
import com.vostroi.util.MicroServiceName;
import com.vostroi.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2021/5/15 15:37
 * @projectName scloud2021
 * @title: CustomerMobileController
 * @description: 手机端用户服务API
 * 注意：extends BaseController<Customer , Long> 必须带上泛型，不然spring mvc会将请求中的id转成String
 */
@Slf4j
@RestController
@RequestMapping(value = "/cust/mbl")
public class CustomerMobileController extends BaseController<Customer, Long> {
    @Autowired private CustomerMobileService service;
    @Autowired  private ProductMobileClient productMobileClient;
    @Override
    public BaseService getService() {
        return service;
    }

    @GetMapping(value = "/prd/{skuId}")
    public ResultData<Product> listProduct(@PathVariable("skuId") Long skuId){
        return this.getRestTemplate().getForObject(MicroServiceName.MICRO_SERVER_PRODUCT + "/prd/mbl/get/" + skuId, ResultData.class);
    }

    @GetMapping(value = "/prd/dtl/{skuId}")
    public ResultData<Product> productDetail(@PathVariable("skuId") Long skuId){

        ResponseEntity<ResultData> entity = this.getRestTemplate().getForEntity(MicroServiceName.MICRO_SERVER_PRODUCT+ "/prd/mbl/dtl/{1}", ResultData.class,skuId);
        log.info("status_code={},status={}",entity.getStatusCode(),entity.getStatusCodeValue());
        ResultData body = entity.getBody();
        log.info("body={}" , body);

        return this.getRestTemplate().getForObject(MicroServiceName.MICRO_SERVER_PRODUCT + "/prd/mbl/dtl/" + skuId, ResultData.class);
    }

    /**
     * 测试使用 Feign 调用
     * @param skuId
     * @return
     */
    @GetMapping(value="/fei/prd/dtl/{skuId}")
    public ResultData<String> productDetailFeign(@PathVariable("skuId") Long skuId){
        return productMobileClient.getSkuDetail(skuId);
    }

    /**
     * 测试使用 Feign 调用  抽象了 BaseFeignClient 无法正常工作
     * @param productId
     * @return
     */
//    @GetMapping(value="/fei/prd/get/{productId}")
//    public ResultData<Product> productGetFeign(@PathVariable("productId") String productId){
//        return productMobileClient.get(productId);
//    }

    /**
     * Feign 客户端默认1秒超时
     * @param skuId
     * @return
     */
    @GetMapping(value="/fei/timeout/prd/dtl/{skuId}")
    public ResultData<String> productDetailFeignTimeOut(@PathVariable("skuId") Long skuId){
        return productMobileClient.getSkuDetailTimeout(skuId);
    }

    @GetMapping(value="/hystrix/right/prd/dtl/{skuId}")
    public ResultData<String> productDetailHystrixRight(@PathVariable("skuId") Long skuId){
        return productMobileClient.getSkuDetailHystrixRight(skuId);
    }

    @GetMapping(value="/hystrix/error/prd/dtl/{skuId}")
    // 被调用服务超时，异常都会触发（本方法出错也会触发）
    @HystrixCommand(fallbackMethod = "timeOutHandler" , commandProperties = {
            // 定义调用provider接口超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public ResultData<String> productDetailHystrixError(@PathVariable("skuId") Long skuId){
        return productMobileClient.getSkuDetailHystrixError(skuId);
    }

}
