package com.whiplash.components.product.util;

import com.whiplash.components.product.bean.Product;
import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.core.commom.util.EnumConstantProduct;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @date 2021/10/31 12:08
 * @projectName whiplash
 * @title: ProductUtil
 * @description: Product 模块 工具类
 */
@Slf4j
public class ProductUtil {

    /**
     * 商品是否 上架 可售
     * @param product
     * @return
     */
    public static boolean isProductOn(Product product) {
        if (product == null) {
            log.info("isProductOn product is null");
            return false;
        }

        if (product.getStatus().getValue() != EnumConstantProduct.PRODUCT_STATUS.ON.getValue()) {
            log.info("isProductOn product {} is not for sale",product.getId());
            return false;
        }

        return true;
    }

    /**
     * 商品是否 上架 可售
     * @param ps
     * @return
     */
    public static boolean isSpecOn(ProductSpecs ps) {
        if (ps == null) {
            log.info("isProductOn ProductSpecs is null");
            return false;
        }

        if (ps.getStatus().getValue() != EnumConstantProduct.PRODUCT_STATUS.ON.getValue()) {
            log.info("isProductOn ProductSpecs {} is not for sale", ps.getId());
            return false;
        }

        return true;
    }

    /**
     * 检查 商品 及 规格 是否上架 可售
     * @param product
     * @param ps
     * @return
     */
    public static boolean isProductAndSpecOn(Product product, ProductSpecs ps) {
        boolean po = isProductOn(product);
        if (!po) {
            return po;
        }

        boolean pso = isSpecOn(ps);

        return pso;
    }

}
