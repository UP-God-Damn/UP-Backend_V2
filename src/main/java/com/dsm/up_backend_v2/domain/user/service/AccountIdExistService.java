package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AccountIdExistService {
    private final UserRepository userRepository;

    public boolean exist(String accountId) {
        return (userRepository.existsByAccountId(accountId));
    }
}