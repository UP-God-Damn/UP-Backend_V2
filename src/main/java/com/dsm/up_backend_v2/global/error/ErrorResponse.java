package com.dsm.up_backend_v2.global.error;

import com.dsm.up_backend_v2.global.error.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {

    private final Integer status;
    private final String errorMessage;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .errorMessage(errorCode.getErrorMessage())
                .status(errorCode.getStatusCode())
                .build();
    }

    public static ErrorResponse of(Exception e) {
        return ErrorResponse.builder()
                .errorMessage("Internal Server Error")
                .status(500)
                .build();
    }
}