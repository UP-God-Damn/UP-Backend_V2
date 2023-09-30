package com.dsm.up_backend_v2.global.security.jwt.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class NotAccessTokenException extends BaseException {
    public static final BaseException EXCEPTION = new NotAccessTokenException();

    public NotAccessTokenException() {
        super(ErrorCode.NOT_ACCESS_TOKEN);
    }
}
