package com.poc.auth.application.usescase;

import com.poc.auth.application.dtos.LoginRequest;
import com.poc.auth.application.dtos.LoginResponse;
import com.poc.auth.domain.User;
import com.poc.auth.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUser {
    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenerateToken generateToken;

    public LoginResponse execute(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new LoginResponse(generateToken.execute(user));
    }
}