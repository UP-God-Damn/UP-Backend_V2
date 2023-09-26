package com.dsm.up_backend_v2.global.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    private final Integer status;
    private final String errorMessage;
}