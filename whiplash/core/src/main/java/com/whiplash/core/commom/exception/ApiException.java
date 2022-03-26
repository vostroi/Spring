package com.whiplash.core.commom.exception;

import com.whiplash.core.commom.util.CommomConstant;

/**
 * @author Administrator
 * @date 2021/12/1 11:36
 * @projectName whiplash
 * @title: ApiException
 * @description: 接口内部异常
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
        super(CommomConstant.STR_API_ERROR);
    }
}
