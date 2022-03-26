package com.vostroi.nimbusjosejwt.jwt.exception;

/**
 *
 */
public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException(String message) {
        super(message);
    }
}
