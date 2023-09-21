package com.dsm.up_backend_v2.global.security.auth;

import com.dsm.up_backend_v2.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) {
        return userRepository.findByAccountId(accountId)
                .map(AuthDetails::new)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }
}
