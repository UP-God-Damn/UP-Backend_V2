package com.dsm.up_backend_v2.domain.post.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class UserNotMatchException extends BaseException {
    public static final UserNotMatchException EXCEPTION = new UserNotMatchException();

    public UserNotMatchException() {
        super(ErrorCode.USER_NOT_MATCH_);
    }
}