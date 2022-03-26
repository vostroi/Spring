package com.whiplash.product.components.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.whiplash.components.order.dto.SingleOrderSubmitDto;
import com.whiplash.components.product.bean.Product;
import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.components.product.util.ProductUtil;
import com.whiplash.core.commom.util.EnumConstant;
import com.whiplash.core.commom.util.ProductConstant;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.platform.bean.BaseDto;
import com.whiplash.core.platform.dao.BaseDao;
import com.whiplash.dto.ProductDto;
import com.whiplash.dto.ProductSpecsDto;
import com.whiplash.product.components.dao.ProductSpecsDao;
import com.whiplash.product.service.ProductMobileService;
import com.whiplash.product.service.ProductSpecsMobileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/10/26 16:09
 * @projectName whiplash
 * @title: ProductSpecsMobileServiceImpl
 * @description: TODO
 */
@Service
@Slf4j
public class ProductSpecsMobileServiceImpl implements ProductSpecsMobileService {
    @Autowired private ProductSpecsDao psDao;

    @Autowired private ProductMobileService pmService;

    @Override
    public BaseDao<ProductSpecs, Long> getDao() {
        return psDao;
    }

    @Override
    public ProductSpecs getUseCache(Long id) {
        // 从缓存中获取


        return get(id);
    }

    @Override
    public ResultData<List<ProductSpecsDto>> listAllSpecByProductId(Long productId) {
        if (productId == null) {
            return ResultData.getResultDataParamEmpty();
        }

        Sort sort = Sort.by(Sort.Direction.ASC, "orderNum");
        List<ProductSpecs> list = psDao.findAllByProductId(productId, sort);
        if(list != null && !list.isEmpty()){
            String json = JSONUtil.toJsonStr(list);
            JSONArray jsonArr = JSONUtil.parseArray(json);
            List<ProductSpecsDto> specsDtos = JSONUtil.toList(jsonArr, ProductSpecsDto.class);
            return ResultData.getResultData(specsDtos);
        }

        return ResultData.getResultDataNotExist();
    }

    @Override
    public ResultData<ProductDto> listAllSpecWithProductByProductId( Long productId) {
        if (productId == null) {
            return ResultData.getResultDataParamEmpty();
        }

        Product prod = pmService.getUseCache(productId);
        if (prod == null) {
            return ResultData.getResultDataNotExist();
        }

        Sort sort = Sort.by(Sort.Direction.ASC, "orderNum");
        List<ProductSpecs> list = psDao.findAllByProductId(productId, sort);


        ProductDto pd = (ProductDto) BaseDto.fromBaseEntityFull(prod , ProductDto.class);
        pd.setSpecs(list);

        return ResultData.getResultData(pd);
    }

    /**
     * isolation 事务隔离级别
     * @param bd
     * @return
     */
    @Transactional(rollbackFor = Exception.class , isolation = Isolation.READ_COMMITTED , propagation = Propagation.REQUIRED)
    @Override
    public ResultData<Boolean> orderedStock(BaseDto bd) {
        if (! (bd instanceof SingleOrderSubmitDto)) {
            return ResultData.getResultDataError();
        }

        SingleOrderSubmitDto sosd = (SingleOrderSubmitDto) bd;

        Product prod = pmService.getByIdLock(sosd.getProdId());

        ProductSpecs ps = psDao.findByIdLock(sosd.getSpecsId());

        if (!ProductUtil.isProductAndSpecOn(prod, ps)) {
            return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333, ProductConstant.PRODUCT_SPEC_NOT_FOR_SALE);
        }

        if (ps.getStockLeft().intValue() < sosd.getSkuNum()) {
            return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333, ProductConstant.SPECS_NOT_ENOUGH);
        }

        // 增加销量
        prod.setSales( ( prod.getSales()==null? BigInteger.ZERO : prod.getSales() ).add(new BigInteger(sosd.getSkuNum().toString())) );
        ps.setSales( ( ps.getSales()==null? BigInteger.ZERO : ps.getSales() ).add(new BigInteger(sosd.getSkuNum().toString())) );

        // 冻结库存
        ps.setStockLeft(ps.getStockLeft().subtract(new BigInteger(sosd.getSkuNum().toString())));
        ps.setStockFrozen(ps.getStockFrozen().add(new BigInteger(sosd.getSkuNum().toString())));

        pmService.save(prod);

        save(ps);

        return ResultData.getResultData(true);
    }
}
