package com.whiplash.product.components.controller;

import com.whiplash.components.order.dto.SingleOrderSubmitDto;
import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.core.commom.spring.PagerUtil;
import com.whiplash.core.commom.util.CommonResult;
import com.whiplash.core.commom.util.ProductConstant;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.platform.controller.BaseController;
import com.whiplash.core.platform.service.BaseService;
import com.whiplash.dto.ProductDto;
import com.whiplash.dto.ProductSpecsDto;
import com.whiplash.product.service.ProductMobileService;
import com.whiplash.product.service.ProductSpecsMobileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/10/26 16:19
 * @projectName whiplash
 * @title: ProductSpecsMobileController
 * @description: TODO
 */
@Slf4j
@RestController
@RequestMapping(value = "/spec/mbl")
public class ProductSpecsMobileController extends BaseController<ProductSpecs,Long> {
    @Autowired private ProductSpecsMobileService psmService;
    @Autowired private ProductMobileService pmService;

    @Override
    public BaseService<ProductSpecs, Long> getService() {
        return psmService;
    }


    @GetMapping(value = "/page/{page}/{size}")
    public ResultData<List<ProductSpecs>> listByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        Pageable pager = PagerUtil.initPage(page, size, null, null);
        CommonResult<Page<ProductSpecs>> cr = psmService.page(pager);
        return ResultData.getResultData(cr.getT().getContent());
    }


    /**
     * 获取指定商品的所有规格
     * @param productId
     * @return
     */
    @GetMapping(value="/list/prod/{prodId}")
    public ResultData<List<ProductSpecsDto>> listAllSpecByProductId(@PathVariable("prodId") Long productId) {
        return psmService.listAllSpecByProductId(productId);
    }

    /**
     * 获取指定商品及其所有规格
     * @param productId
     * @return
     */
    @GetMapping(value="/list/prod/ctn/{prodId}")
    public ResultData<ProductDto> listAllSpecWithProductByProductId(@PathVariable("prodId") Long productId) {
        return psmService.listAllSpecWithProductByProductId(productId);
    }

    /**
     * 订单成功 处理库存
     * @param sosd
     * @return
     */
    @PostMapping(value = "/ordered/stock")
    public ResultData<Boolean> orderedStock(@RequestBody SingleOrderSubmitDto sosd) {
        ResultData<Boolean> rd = psmService.orderedStock(sosd);
        if (rd.getData() != null && rd.getData()) {
            rd.setMessage(ProductConstant.SPECS_FROZEN_STOCK_SUCCESS);
        }else{
            rd.setData(false);
            rd.setMessage(ProductConstant.SPECS_FROZEN_STOCK_SUCCESS);
        }

        return rd;
    }

}
