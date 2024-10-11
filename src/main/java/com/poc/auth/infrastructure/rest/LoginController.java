package com.poc.auth.infrastructure.rest;

import com.poc.auth.application.dtos.LoginRequest;
import com.poc.auth.application.dtos.LoginResponse;
import com.poc.auth.application.usescase.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController implements LoginResource {
    private final LoginUser loginUser;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return loginUser.execute(loginRequest);
    }
}
