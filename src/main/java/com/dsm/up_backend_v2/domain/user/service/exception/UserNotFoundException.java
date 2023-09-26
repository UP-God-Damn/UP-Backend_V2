package com.dsm.up_backend_v2.domain.user.service.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class UserNotFoundException extends BaseException {

    private final BaseException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
