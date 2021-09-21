package com.whiplash.core.commom.exception;

/**
 * @author Administrator
 * @date 2021/9/3 16:20
 * @projectName whiplash
 * @title: PropertiesSetException
 * @description: bean 属性设置异常
 */
public class PropertiesSetException extends RuntimeException {
    public PropertiesSetException(String message) {
        super(message);
    }
}
