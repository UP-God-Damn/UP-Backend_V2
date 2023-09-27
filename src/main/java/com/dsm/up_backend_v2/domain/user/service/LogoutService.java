package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.domain.RefreshToken;
import com.dsm.up_backend_v2.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up_backend_v2.domain.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void logout(String accountId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(accountId) .orElseThrow(() -> new UserNotFoundException());

        refreshTokenRepository.delete(refreshToken);
    }
}
