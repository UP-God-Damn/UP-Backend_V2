package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.domain.RefreshToken;
import com.dsm.up_backend_v2.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.domain.user.service.exception.JwtInvalidException;
import com.dsm.up_backend_v2.domain.user.service.exception.RefreshTokenNotFoundException;
import com.dsm.up_backend_v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class RefreshService {
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse refresh(String refreshToken) {
        if(!jwtProvider.validate(refreshToken)) throw JwtInvalidException.EXCEPTION;
        RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String accountId = token.getAccountId();
        String nAccessToken = jwtProvider.generateToken(accountId);
        String nRefreshToken = jwtProvider.generateRefreshToken(accountId);

        return TokenResponse.builder()
                .accessToken(nAccessToken)
                .refreshToken(nRefreshToken)
                .build();

    }
}
