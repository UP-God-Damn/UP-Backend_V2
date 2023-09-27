package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.domain.RefreshToken;
import com.dsm.up_backend_v2.domain.user.domain.User;
import com.dsm.up_backend_v2.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up_backend_v2.domain.user.domain.repository.UserRepository;
import com.dsm.up_backend_v2.domain.user.presentation.dto.request.LoginRequest;
import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.domain.user.service.exception.PasswordMisMatchException;
import com.dsm.up_backend_v2.domain.user.service.exception.UserNotFoundException;
import com.dsm.up_backend_v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId()).orElseThrow(() -> new UserNotFoundException());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordMisMatchException();
        }

        return TokenResponse.builder()
                .accessToken(jwtProvider.generateToken(user.getAccountId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .accountId(user.getAccountId())
                        .refreshToken(jwtProvider.generateToken(user.getAccountId()))
                        .build()).getRefreshToken())
                .build();
    }
}
