package com.whiplash.elastic.service;

import com.whiplash.elastic.index.ProductSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Administrator
 * @date 2021/12/7 16:20
 * @projectName whiplash
 * @title: ProductSpecsService
 * @description: TODO
 */
public interface ProductSpecsEsService extends IndexBaseService<ProductSpecs ,Long> {


    public Page<ProductSpecs> getByProductId(Long productId , Pageable pager);

    public void putBatch(Integer page, Integer size);


}
