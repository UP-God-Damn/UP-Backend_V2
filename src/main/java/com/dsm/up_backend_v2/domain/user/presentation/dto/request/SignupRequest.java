package com.dsm.up_backend_v2.domain.user.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SignupRequest {

    @NotBlank
    @Size(min = 2, max = 12)
    private String accountId;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    @Size(min = 2, max = 12)
    private String nickname;
}
