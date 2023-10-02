package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.domain.user.service.exception.JwtInvalidException;
import com.dsm.up_backend_v2.domain.user.service.exception.UserNotFoundException;
import com.dsm.up_backend_v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class RefreshService {
    private final JwtProvider jwtProvider;

    public TokenResponse refresh(String refreshToken) {
        if(!jwtProvider.validate(refreshToken)) throw JwtInvalidException.EXCEPTION;

        String subject = jwtProvider.getSubject(refreshToken);
        String nAccessToken = jwtProvider.generateToken(subject);
        String nRefreshToken = jwtProvider.generateRefreshToken(subject);

        return TokenResponse.builder()
                .accessToken(nAccessToken)
                .refreshToken(nRefreshToken)
                .build();

    }
}
