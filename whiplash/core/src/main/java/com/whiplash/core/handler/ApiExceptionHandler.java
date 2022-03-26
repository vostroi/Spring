package com.whiplash.core.handler;

import com.whiplash.core.commom.exception.ApiException;
import com.whiplash.core.commom.util.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Administrator
 * @date 2021/12/1 13:08
 * @projectName whiplash
 * @title: ApiExceptionHandler
 * @description: 全局 接口 异常 处理
 */
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler<T> {

    @ResponseStatus(HttpStatus.OK)
    public ResultData returnSuccess() {
        return ResultData.getResultDataSuccess();
    }

    @ResponseStatus(HttpStatus.OK)
    public ResultData returnSuccess(T t) {
        return ResultData.getResultDataSuccess(t);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData returnExceptionSystem(Exception e) {
        return ResultData.exception(null, e.getMessage());
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData returnApiException(ApiException ae) {
        return ResultData.exception(null, ae.getMessage());
    }







}
