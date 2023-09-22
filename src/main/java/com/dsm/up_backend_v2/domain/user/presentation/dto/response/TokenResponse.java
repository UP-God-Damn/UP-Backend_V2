package com.dsm.up_backend_v2.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponse {

    private final String refreshToken;
    private final String accessToken;

    @Builder
    public TokenResponse (String refreshToken, String accessToken){
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
