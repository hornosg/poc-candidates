package com.poc.auth.infrastructure.rest;

import com.poc.auth.application.dtos.LoginRequest;
import com.poc.auth.application.dtos.LoginResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Request has missing or invalid information"),
        @ApiResponse(responseCode = "401", description = "Invalid authentication error"),
        @ApiResponse(responseCode = "403", description = "User not authorized for using this operation"),
        @ApiResponse(responseCode = "404", description = "The resource was not found"),
        @ApiResponse(responseCode = "500", description = "Internal error at request processing time"),
        @ApiResponse(responseCode = "503", description = "Service is temporally not available")
})
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "API para gestionar la autenticación")
public interface AuthApi {

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest);

}
