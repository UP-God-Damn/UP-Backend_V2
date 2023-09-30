package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.domain.user.service.exception.UserNotFoundException;
import com.dsm.up_backend_v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {
    private final JwtProvider jwtProvider;

    public TokenResponse refresh(String refreshToken) {
        if(!jwtProvider.validate(refreshToken)) throw UserNotFoundException.EXCEPTION;
        String nAccessToken = jwtProvider.generateToken(jwtProvider.getSubject(refreshToken));
        String nRefreshToken = jwtProvider.generateRefreshToken(jwtProvider.getSubject(refreshToken));

        return TokenResponse.builder()
                .accessToken(nAccessToken)
                .refreshToken(nRefreshToken)
                .build();

    }
}
