package com.whiplash.product.service;

import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.core.commom.util.CommonResult;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.platform.bean.BaseDto;
import com.whiplash.core.platform.service.BaseService;
import com.whiplash.dto.ProductDto;
import com.whiplash.dto.ProductSpecsDto;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/10/26 15:57
 * @projectName whiplash
 * @title: ProductSpecsMobileService
 * @description: TODO
 */
public interface ProductSpecsMobileService extends BaseService<ProductSpecs, Long> {

    /**
     * 根据商品ID获取其所有的规格
     * @param productId
     * @return
     */
    public ResultData<List<ProductSpecsDto>> listAllSpecByProductId(Long productId);

    /**
     * 根据商品ID获取商品及其所有规格信息
     * @param productId
     * @return
     */
    public ResultData<ProductDto> listAllSpecWithProductByProductId( Long productId);

    /**
     * 直接下单 下单成功后 检查并锁定库存
     * @param bd  SingleOrderSubmitDto
     * @return
     */
    public ResultData<Boolean> orderedStock(BaseDto bd);

}
