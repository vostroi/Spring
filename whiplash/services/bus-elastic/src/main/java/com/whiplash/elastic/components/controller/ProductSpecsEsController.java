package com.whiplash.elastic.components.controller;

import com.whiplash.components.product.feign.ProductSpecsMobileClient;
import com.whiplash.core.commom.spring.PagerUtil;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.commom.util.WhiplashBeanUtil;
import com.whiplash.elastic.index.ProductSpecs;
import com.whiplash.elastic.service.ProductSpecsEsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/12/7 16:53
 * @projectName whiplash
 * @title: ProductSpecsEsController
 * @description: TODO
 */
@Slf4j
@RestController
@RequestMapping(value = "/ps")
public class ProductSpecsEsController {
    @Autowired private ProductSpecsEsService psEsService;
    @Autowired private ProductSpecsMobileClient psmClient;

    /**
     * 根据 商品 ID 搜索出所有 商品规格
     * @param productId
     * @return
     */
    @GetMapping(value = "/getByProductId/{productId}")
    public ResultData<Page<ProductSpecs>> searchByProductId(
            @PathVariable("productId") Long productId,
            @RequestParam(value = "page" , required = false , defaultValue = "1") Integer page,
            @RequestParam(value = "size" , required = false , defaultValue = "10") Integer size,
            @RequestParam(value = "sort" , required = false , defaultValue = "crtTime") String sort,
            @RequestParam(value = "order" , required = false , defaultValue = "DESC") String order
            ) {
        Pageable pager = PagerUtil.initPage(page, size, sort, order);
        Page<ProductSpecs> pageData = psEsService.getByProductId(productId, pager);
        return ResultData.getResultData(pageData);
    }

    @GetMapping(value = "/get/{id}")
    public ResultData<ProductSpecs> getByProductId(@PathVariable("id") Long id ) {
        ProductSpecs ps = psEsService.getById(id);
        return ResultData.getResultData(ps);
    }

    @GetMapping(value = "/delete/{id}")
    public ResultData<Object> deleteById(@PathVariable("id") Long id) {
        psEsService.delete(id);
        return ResultData.getResultDataSuccess();
    }

    @GetMapping(value = "/put/{id}")
    public ResultData<ProductSpecs> putIntoIndex(@PathVariable("id") Long id) {
        ResultData<com.whiplash.components.product.bean.ProductSpecs> rd = psmClient.getById(id);
        ProductSpecs psDoc = WhiplashBeanUtil.toBean(rd, ProductSpecs.class);
        boolean result = psEsService.save(psDoc);
        return ResultData.getResultData(result ? psDoc : null);
    }

    @GetMapping(value = "/put/batch/{page}/{size}")
    public ResultData putBatch(@PathVariable("page") Integer page , @PathVariable("size") Integer size) {
        psEsService.putBatch(page , size);

        return ResultData.getResultDataSuccess();
    }

    @GetMapping(value = "/search/{key}")
    public ResultData<List<ProductSpecs>> search(@PathVariable("key") String key) {
        Pageable pager = PagerUtil.initPage(1, 100, null, null);
        Page<ProductSpecs> pageData = psEsService.search(key , pager);
        return ResultData.getResultData(pageData.getContent());
    }
    @GetMapping(value = "/searchAggregation/{key}")
    public ResultData<List<ProductSpecs>> searchAggregation(@PathVariable("key") String key) {
        Pageable pager = PagerUtil.initPage(1, 100, null, null);
        Page<ProductSpecs> pageData = psEsService.searchAggregation(key , pager);
        return ResultData.getResultData(pageData.getContent());
    }
}
