package com.dsm.up_backend_v2.domain.user.presentation;

import com.dsm.up_backend_v2.domain.user.presentation.dto.request.LoginRequest;
import com.dsm.up_backend_v2.domain.user.presentation.dto.request.SignupRequest;
import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.domain.user.service.ExistService;
import com.dsm.up_backend_v2.domain.user.service.LoginService;
import com.dsm.up_backend_v2.domain.user.service.LogoutService;
import com.dsm.up_backend_v2.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final SignUpService signUpService;
    private final LoginService loginService;
    private final ExistService existService;
    private final LogoutService logoutService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signup(@RequestBody @Valid SignupRequest request) {
        return signUpService.signUp(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

    @GetMapping("/{id}")
    public void exist(@PathVariable(value = "id") String accountId) {
        existService.exist(accountId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@PathVariable(value = "id") String accountId) {
        logoutService.logout(accountId);
    }

}
