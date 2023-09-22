package com.dsm.up_backend_v2.domain.user.presentation;

import com.dsm.up_backend_v2.domain.user.presentation.dto.request.SignupRequest;
import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.domain.user.service.SignUpService;
import com.dsm.up_backend_v2.global.security.auth.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final SignUpService signUpService;

    @PostMapping("/signup")
    public TokenResponse signUp(@RequestBody SignupRequest request) {
        return signUpService.signUp(request);
    }

}
