package com.whiplash.elastic.components.repository;

import com.whiplash.elastic.index.ProductSpecs;
import com.whiplash.elastic.repository.IndexBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Administrator
 * @date 2021/12/7 16:13
 * @projectName whiplash
 * @title: ProductSpecsRepository
 * @description:
 */

public interface ProductSpecsRepository extends IndexBaseRepository<ProductSpecs , Long> {

    public Page<ProductSpecs> findByProductId(Long productId, Pageable pager);

    public Page<ProductSpecs> findBySpecsName(String key, Pageable pager);


}
