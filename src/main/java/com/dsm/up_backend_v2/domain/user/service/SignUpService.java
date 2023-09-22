package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.domain.RefreshToken;
import com.dsm.up_backend_v2.domain.user.domain.User;
import com.dsm.up_backend_v2.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up_backend_v2.domain.user.domain.repository.UserRepository;
import com.dsm.up_backend_v2.domain.user.presentation.dto.request.SignupRequest;
import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenResponse signUp(SignupRequest request) {
        if(userRepository.existsByAccountId(request.getAccountId())) throw new RuntimeException("AccountId_NOT_EXSIST");

        User user = userRepository.save(User.builder()
                        .nickName(request.getNickName())
                        .accountId(request.getAccountId())
                        .password(passwordEncoder.encode(request.getPassword()))
                .build());

        return TokenResponse.builder()
                .accessToken(jwtProvider.generateToken(user.getAccountId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .accountId(user.getAccountId())
                        .refreshToken(jwtProvider.generateRefreshToken(user.getAccountId()))
                        .build()).getRefreshToken())
                .build();
    }
}
