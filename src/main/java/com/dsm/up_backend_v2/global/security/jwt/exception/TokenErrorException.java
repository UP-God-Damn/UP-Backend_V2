package com.dsm.up_backend_v2.global.security.jwt.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class TokenErrorException extends BaseException {
    public static final BaseException EXCEPTION = new TokenErrorException();

    public TokenErrorException() {
        super(ErrorCode.TOKEN_ERROR);
    }
}
