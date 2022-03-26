package com.whiplash.elastic.components.service;

import com.google.common.collect.Lists;
import com.whiplash.components.product.feign.ProductSpecsMobileClient;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.commom.util.WhiplashBeanUtil;
import com.whiplash.elastic.components.repository.ProductSpecsRepository;
import com.whiplash.elastic.index.ProductSpecs;
import com.whiplash.elastic.repository.IndexBaseRepository;
import com.whiplash.elastic.service.ProductSpecsEsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2021/12/7 16:25
 * @projectName whiplash
 * @title: ProductSpecsServiceImpl
 * @description: es 商品搜索 业务
 */
@Slf4j
@Service
public class ProductSpecsEsServiceImpl implements ProductSpecsEsService {
    @Autowired private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired private ProductSpecsRepository psRepository;
    @Autowired private ProductSpecsMobileClient psmClient;
    //@Autowired private ElasticsearchOperations elasticsearchOperations;


    @Override
    public IndexBaseRepository<ProductSpecs, Long> getRepository() {
        return psRepository;
    }

    @Override
    public ElasticsearchRestTemplate getElasticSearchRestTemplate() {
        return elasticsearchRestTemplate;
    }

    @Override
    public Page<ProductSpecs> getByProductId(Long productId, Pageable pager) {
        if (productId == null || pager == null) {
            return null;
        }
        return psRepository.findByProductId(productId, pager);
    }


    /**
     * 搜索
     * 搜索字段是 test，分词会全部匹配
     * @param key
     * @return
     */
    @Override
    public Page<ProductSpecs> search(String key , Pageable pager) {
        return psRepository.findBySpecsName(key , pager);
    }

    @Override
    public Page<ProductSpecs> searchAggregation(String key, Pageable pager) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // 查询
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        // 过滤器
        BoolQueryBuilder filter = QueryBuilders.boolQuery();
        // 价格范围
        RangeQueryBuilder priceQuery = QueryBuilders.rangeQuery("price").from(0).to(500);
        // 关键字
        MatchQueryBuilder nameQuery = QueryBuilders.matchQuery("specsName", key).analyzer("ik_smart").operator(Operator.AND);
        // 精准匹配
        // filter.must(QueryBuilders.termQuery("brandCh", "华为"));

        nativeSearchQueryBuilder
//                .withQuery(nameQuery)
                .withFilter(filter.must(priceQuery).must(nameQuery))
                .withPageable(pager)
                .withSorts(SortBuilders.fieldSort("price").order(SortOrder.ASC))
                ;

        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        SearchHits<ProductSpecs> searchHits = elasticsearchRestTemplate.search(query, ProductSpecs.class);

        // 输出 查询语句
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String dsl = sourceBuilder.query(query.getQuery()).query(query.getFilter()).toString();
        log.info("dsl={}", dsl);

        Page<ProductSpecs> pageData = new PageImpl<>(searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList()), pager, searchHits.getTotalHits());
        return pageData;
    }

    @Override
    public void putBatch(Integer page, Integer size) {
        if (page == null || size == null) {

        } else {
            ResultData<List<com.whiplash.components.product.bean.ProductSpecs>> rd = psmClient.listByPage(page, size);

            if(rd!=null && rd.getData()!=null && !rd.getData().isEmpty()){
                List<ProductSpecs> psList = Lists.newArrayList();
                rd.getData().forEach(p->{
                    ProductSpecs psDoc = WhiplashBeanUtil.toBean(p, ProductSpecs.class);
                    psList.add(psDoc);
                });

                if (!psList.isEmpty()) {
                    saveBatch(psList);
                }
            }

        }
    }
}
