package com.dsm.up_backend_v2.global.aws.exception;

import com.dsm.up_backend_v2.global.error.exception.BaseException;
import com.dsm.up_backend_v2.global.error.exception.ErrorCode;

public class ImageUploadFailException extends BaseException {
    public static final ImageUploadFailException EXCEPTION = new ImageUploadFailException();

    private ImageUploadFailException() {
        super(ErrorCode.IMAGE_UPLOAD_FAIL);
    }
}
