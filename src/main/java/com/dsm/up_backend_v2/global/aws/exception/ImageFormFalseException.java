package com.dsm.up_backend_v2.global.aws.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class ImageFormFalseException extends BaseException {
    public static final ImageFormFalseException EXCEPTION = new ImageFormFalseException();

    public ImageFormFalseException() {
        super(ErrorCode.IMAGE_FORM_FALSE);
    }
}
