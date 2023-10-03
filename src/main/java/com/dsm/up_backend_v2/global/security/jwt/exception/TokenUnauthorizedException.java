package com.dsm.up_backend_v2.global.security.jwt.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class TokenUnauthorizedException extends BaseException {
    public static final BaseException EXCEPTION = new TokenUnauthorizedException();

    public TokenUnauthorizedException() {
        super(ErrorCode.TOKEN_UNAUTHORIZED);
    }
}
