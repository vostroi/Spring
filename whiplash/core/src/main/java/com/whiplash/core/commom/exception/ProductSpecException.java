package com.whiplash.core.commom.exception;

/**
 * @author Administrator
 * @date 2021/10/31 12:49
 * @projectName whiplash
 * @title: ProductSpecException
 * @description: 商品 商品规格 处理异常
 */
public class ProductSpecException extends RuntimeException {
    public ProductSpecException(String message) {
        super(message);
    }
}
