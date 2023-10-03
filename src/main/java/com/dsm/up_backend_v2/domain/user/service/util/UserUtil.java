package com.dsm.up_backend_v2.domain.user.service.util;

import com.dsm.up_backend_v2.domain.user.domain.User;
import com.dsm.up_backend_v2.domain.user.domain.repository.UserRepository;
import com.dsm.up_backend_v2.domain.user.service.exception.JwtInvalidException;
import com.dsm.up_backend_v2.domain.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) throw JwtInvalidException.EXCEPTION;

        return authentication.getName();
    }

    public User findUser() {
        return userRepository.findByAccountId(getUserId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
