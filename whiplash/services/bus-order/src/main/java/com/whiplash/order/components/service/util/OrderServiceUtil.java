package com.whiplash.order.components.service.util;

import cn.hutool.db.ds.pooled.PooledDSFactory;
import cn.hutool.extra.spring.SpringUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.whiplash.components.order.bean.MerchantOrder;
import com.whiplash.components.order.bean.Order;
import com.whiplash.components.order.bean.OrderProduct;
import com.whiplash.components.order.bean.StoreOrder;
import com.whiplash.components.order.dto.SingleOrderSubmitDto;
import com.whiplash.components.product.bean.Product;
import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.components.product.feign.ProductMobileClient;
import com.whiplash.components.product.feign.ProductSpecsMobileClient;
import com.whiplash.core.commom.util.*;
import com.whiplash.service.ProductMobileService;
import com.whiplash.service.ProductSpecsMobileService;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author Administrator
 * @date 2021/10/26 15:04
 * @projectName whiplash
 * @title: OrderServiceUtil
 * @description: OrderService工具类
 */
@Slf4j
public class OrderServiceUtil {
    private static ProductSpecsMobileClient psmClient = null;
    private static ProductMobileClient pmClient = null;

    public static ProductSpecsMobileClient getPsmClient() {
        if(psmClient == null){
            psmClient = SpringUtil.getBean(ProductSpecsMobileClient.class);
        }
        return psmClient;
    }

    public static ProductMobileClient getPmClient() {
        if (pmClient == null) {
            pmClient = SpringUtil.getBean(ProductMobileClient.class);
        }
        return pmClient;
    }

    /**
     * 生成临时数据  Order
     * @param sosd
     * @return
     */
    public static CommonResult<Order> crtOrder(SingleOrderSubmitDto sosd) {
        Order order = null;
        if (sosd == null || sosd.getProdId() == null || sosd.getSpecsId() == null || sosd.getSkuNum() == null || sosd.getSkuNum() < 1) {
            return CommonResult.fail(CommomConstant.REQUEST_PARAMS_ERROR);
        }
        try {

            // 检查规格及库存
            ResultData<ProductSpecs> psData = getPsmClient().getByIdUseCache(sosd.getSpecsId());
            ProductSpecs ps = psData.getData();
            if (ps == null || ps.getStockLeft().intValue() < sosd.getSkuNum()) {
                return CommonResult.fail(ProductConstant.SPECS_NOT_EXIST_OR_NOT_ENOUGH);
            }
            if(ps.getStatus().getValue() != EnumConstantProduct.PRODUCT_STATUS.ON.getValue()){
                return CommonResult.fail(ProductConstant.SPECS_NOT_ON);
            }

            // 检查商品
            ResultData<Product> pData = getPmClient().getByIdUseCache(sosd.getProdId());
            if (pData.getData() == null || pData.getData().getStatus().getValue() != EnumConstantProduct.PRODUCT_STATUS.ON.getValue()) {
                return CommonResult.fail(ProductConstant.PRODUCT_NOT_EXIST_OR_NOT_ON);
            }

            // 生成主订单
            order = Order.builder()
                    .orderCode(OrderCodeUtil.generateOrderCode())
                    .orderPrice(ps.getPrice().multiply(new BigDecimal(sosd.getSkuNum())))
                    .feeAmount(BigDecimal.ZERO)
                    .buyer(sosd.getCustId())
                    .skuCount(sosd.getSkuNum())
                    .build();
            order.setPayAmount(order.getOrderPrice());
            order.setDiscountAmount(order.getOrderPrice().subtract(order.getPayAmount()));

            // 生成商户订单
            CommonResult<MerchantOrder> moResult = crtMerchantOrder(sosd, ps, pData.getData());
            if(moResult.getHasError()){
                return CommonResult.fail(OrderConstant.MERCHANT_ORDER_CRT_ERROR);
            }

            // 生成 门店订单
            CommonResult<StoreOrder> soResult = crtStoreOrder(sosd, ps, pData.getData());
            if (soResult.getHasError()) {
                return CommonResult.fail(OrderConstant.STORE_ORDER_CRT_ERROR);
            }

            // 生成商品订单
            CommonResult<OrderProduct> opResult = crtOrderProduct(sosd, ps, pData.getData());
            if (opResult.getHasError()) {
                return CommonResult.fail(OrderConstant.ORDER_PRODUCT_CRT_ERROR);
            }

            // 关联门店与订单明细
            StoreOrder so = soResult.getT();
            so.getOpList().add(opResult.getT());

            // 关联商户与门店订单
            MerchantOrder mo = moResult.getT();
            mo.getSoList().add(so);

            // 关联主订单与商户订单
            order.getMoList().add(mo);

        } catch (Exception e) {
            log.error("crtOrder 生成内存订单 异常：",e);
            return CommonResult.fail(OrderConstant.ORDER_CRT_ERROR);
        }
        return CommonResult.getResult(order);
    }

    /**
     * 生成临时数据   MerchantOrder
     * @param sosd
     * @param ps
     * @param p
     * @return
     */
    private static CommonResult<MerchantOrder> crtMerchantOrder(SingleOrderSubmitDto sosd , ProductSpecs ps , Product p) {
        MerchantOrder order = null;
        try {

            order = MerchantOrder.builder()
                    .merchantId(p.getMerchantId())
                    .orderPrice(ps.getPrice().multiply(new BigDecimal(sosd.getSkuNum())))
                    .feeAmount(BigDecimal.ZERO)
                    .buyer(sosd.getCustId())
                    .skuCount(sosd.getSkuNum())
                    .build();
            order.setPayAmount(order.getOrderPrice());
            order.setDiscountAmount(order.getOrderPrice().subtract(order.getPayAmount()));

        } catch (Exception e) {
            log.error("crtMerchantOrder 生成内存订单 异常：",e);
            return CommonResult.fail(OrderConstant.MERCHANT_ORDER_CRT_ERROR);
        }
        return CommonResult.getResult(order);
    }

    /**
     * 生成临时数据 StoreOrder
     * @param sosd
     * @param ps
     * @param p
     * @return
     */
    private static CommonResult<StoreOrder> crtStoreOrder(SingleOrderSubmitDto sosd , ProductSpecs ps , Product p) {
        StoreOrder order = null;
        try {

            order = StoreOrder.builder()
                    .storeId(p.getStoreId())
                    .merchantId(p.getMerchantId())
                    .orderPrice(ps.getPrice().multiply(new BigDecimal(sosd.getSkuNum())))
                    .feeAmount(BigDecimal.ZERO)
                    .buyer(sosd.getCustId())
                    .skuCount(sosd.getSkuNum())
                    .build();
            order.setPayAmount(order.getOrderPrice());
            order.setDiscountAmount(order.getOrderPrice().subtract(order.getPayAmount()));
        } catch (Exception e) {
            log.error("crtStoreOrder 生成内存订单 异常：",e);
            return CommonResult.fail(OrderConstant.STORE_ORDER_CRT_ERROR);
        }
        return CommonResult.getResult(order);
    }


    /**
     * 生成临时数据 OrderProduct
     * @param sosd
     * @param ps
     * @param p
     * @return
     */
    private static CommonResult<OrderProduct> crtOrderProduct(SingleOrderSubmitDto sosd , ProductSpecs ps , Product p) {
        OrderProduct order = null;
        try {

            order = OrderProduct.builder()
                    .storeId(p.getStoreId())
                    .merchantId(p.getMerchantId())
                    .orderPrice(ps.getPrice().multiply(new BigDecimal(sosd.getSkuNum())))
                    .feeAmount(BigDecimal.ZERO)
                    .buyer(sosd.getCustId())
                    .skuCount(sosd.getSkuNum())
                    .productId(p.getId())
                    .productSpecsId(ps.getId())
                    .skuName(p.getSkuName())
                    .productSpecsName(ps.getSpecsName())
                    .build();
            order.setPayAmount(order.getOrderPrice());
            order.setDiscountAmount(order.getOrderPrice().subtract(order.getPayAmount()));
        } catch (Exception e) {
            log.error("crtOrderProduct 生成内存订单 异常：",e);
            return CommonResult.fail(OrderConstant.ORDER_PRODUCT_CRT_ERROR);
        }
        return CommonResult.getResult(order);
    }
}
