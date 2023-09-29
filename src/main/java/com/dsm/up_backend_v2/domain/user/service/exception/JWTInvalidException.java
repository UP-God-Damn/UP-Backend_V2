package com.dsm.up_backend_v2.domain.user.service.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class JWTInvalidException extends BaseException {
    public static final BaseException EXCEPTION = new JWTInvalidException();

    public JWTInvalidException() {
        super(ErrorCode.JWT_INVALID);
    }
}
