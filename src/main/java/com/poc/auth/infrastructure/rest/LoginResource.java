package com.poc.auth.infrastructure.rest;

import com.poc.auth.application.dtos.LoginRequest;
import com.poc.auth.application.dtos.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginResource  extends AuthApi {
    LoginResponse login(@RequestBody LoginRequest loginRequest);
}
