package com.whiplash.order.components.service;

import com.whiplash.components.order.bean.MerchantOrder;
import com.whiplash.components.order.bean.Order;
import com.whiplash.components.order.dto.OrderDto;
import com.whiplash.components.order.dto.SingleOrderSubmitDto;
import com.whiplash.components.product.feign.ProductSpecsMobileClient;
import com.whiplash.core.commom.exception.ProductSpecException;
import com.whiplash.core.commom.util.*;
import com.whiplash.core.platform.bean.BaseDto;
import com.whiplash.core.platform.dao.BaseDao;
import com.whiplash.order.components.dao.OrderMobileDao;
import com.whiplash.order.components.service.util.OrderServiceUtil;
import com.whiplash.service.OrderMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/5 18:18
 * @projectName whiplash
 * @title: OrderMobileServiceImpl
 * @description: TODO
 */
@Service
public class OrderMobileServiceImpl implements OrderMobileService {
    @Autowired private OrderMobileDao ordMblDao;
    @Autowired private ProductSpecsMobileClient psmClient;
    // @Autowired private MerchantOrderService moService；

    @Override
    public BaseDao getDao() {
        return ordMblDao;
    }

    @Override
    public Order getUseCache(Long aLong) {
        // if cache
        return get(aLong);
    }

    /**
     * 直接下单业务处理
     * 1. 生成订单
     * 2. 检查并锁定库存
     * 3. 扣减 | 释放 库存
     * @param sosd
     * @return
     */
    @Transactional(rollbackFor = Exception.class , propagation = Propagation.REQUIRED)
    @Override
    public ResultData<OrderDto> singleSubmitOrder(SingleOrderSubmitDto sosd) {
        if(sosd == null || sosd.getSkuNum() == null || sosd.getSkuNum()<1 || sosd.getSpecsId()==null || sosd.getProdId()  == null || sosd.getCustId() == null){
            return ResultData.getResultDataParamEmpty();
        }

        // 生成临时订单数据
        CommonResult<Order> orderResult = OrderServiceUtil.crtOrder(sosd);
        if (orderResult.getHasError()) {
            return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333, orderResult.getErrorMsg());
        }

        // 保存订单
        Order order = save(orderResult.getT());
        if (order == null) {
            return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333, OrderConstant.ORDER_CRT_ERROR);
        }

        // 由于还没写 merchant store orderproduct 业务 不保存； 另外 不能每次循环都save 到数据库 统一处理
        // 保存商户订单
        List<MerchantOrder> moList = orderResult.getT().getMoList();
        for (MerchantOrder mo : moList) {
            mo.setOrderId(order.getId());
            // save mo

            // 保存门店订单
            mo.getSoList().forEach(so->{
                // so.setMerchantId();

                // save so

                so.getOpList().forEach(op->{
                    // op.setStoreId();
                    // save op
                });
            });

        }



        // 检查并锁定库存
        ResultData<Boolean> stockRd = psmClient.orderedStock(sosd);

        // 库存不够或出错 回滚数据
        if(stockRd.getData() == null || !stockRd.getData()){
            throw new ProductSpecException(ProductConstant.PRODUCT_SPEC_FROZEN_EXCEPTION);
        }

        return ResultData.getResultDataSuccess(BaseDto.fromBaseEntityFull(order, OrderDto.class));
    }

}
