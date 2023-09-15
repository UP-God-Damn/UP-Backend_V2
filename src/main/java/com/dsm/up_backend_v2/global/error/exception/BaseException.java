package com.dsm.up_backend_v2.global.error.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}