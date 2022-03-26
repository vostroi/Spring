package com.vostroi.nimbusjosejwt.jwt.common;

/**
 * 封装API的错误码
 *
 * @author Administrator
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
