package com.dsm.up_backend_v2.domain.user.presentation;

import com.dsm.up_backend_v2.domain.user.presentation.dto.request.LoginRequest;
import com.dsm.up_backend_v2.domain.user.presentation.dto.request.SignupRequest;
import com.dsm.up_backend_v2.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up_backend_v2.domain.user.service.AccountIdExistService;
import com.dsm.up_backend_v2.domain.user.service.LoginService;
import com.dsm.up_backend_v2.domain.user.service.LogoutService;
import com.dsm.up_backend_v2.domain.user.service.RefreshService;
import com.dsm.up_backend_v2.domain.user.service.SignUpService;
import com.dsm.up_backend_v2.domain.user.service.exception.AccountIdAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final SignUpService signUpService;
    private final LoginService loginService;
    private final AccountIdExistService accountIdExistService;
    private final LogoutService logoutService;
    private final RefreshService refreshService;

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
        if(accountIdExistService.exist(accountId)) throw AccountIdAlreadyExistException.EXCEPTION;
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        logoutService.logout();
    }

    @PutMapping("/refresh")
    public TokenResponse refresh(@RequestHeader(value = "refreshToken") String refreshToken) {
        return refreshService.refresh(refreshToken);
    }

}
