package com.dsm.up_backend_v2.domain.user.service.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class AccountIdAlreadyExistException extends BaseException {
    public static final AccountIdAlreadyExistException EXCEPTION = new AccountIdAlreadyExistException();

    public AccountIdAlreadyExistException() {
        super(ErrorCode.ACCOUNT_ID_ALREADY_EXIST);
    }
}
