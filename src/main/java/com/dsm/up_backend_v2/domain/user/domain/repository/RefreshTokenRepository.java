package com.dsm.up_backend_v2.domain.user.domain.repository;

import com.dsm.up_backend_v2.domain.user.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
