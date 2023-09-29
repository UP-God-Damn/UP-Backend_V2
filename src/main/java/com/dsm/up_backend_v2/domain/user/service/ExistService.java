package com.dsm.up_backend_v2.domain.user.service;

import com.dsm.up_backend_v2.domain.user.domain.repository.UserRepository;
import com.dsm.up_backend_v2.domain.user.service.exception.AccountIdAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExistService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public void exist(String accountId) {
        if(userRepository.existsByAccountId(accountId)) throw AccountIdAlreadyExistException.EXCEPTION;
    }

}