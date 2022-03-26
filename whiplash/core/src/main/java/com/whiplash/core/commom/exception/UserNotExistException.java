package com.whiplash.core.commom.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

/**
 * @author Administrator
 * @date 2021/9/12 18:39
 * @projectName whiplash
 * @title: UserNotExistException
 * @description: 用户不存在
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String message) {
        super(message);
    }
}
