package com.poc.candidate.iam.application.usescase;

import com.poc.candidate.iam.domain.User;
import com.poc.candidate.iam.infrastructure.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class FindUser {

    private final UserJpaRepository repository;

    public User invoke(String id) {
        return repository.findById(id).orElseThrow(() -> new InvalidParameterException("user not found"));
    }
    public Optional<User> byUsername(String userName) {
        return repository.findByUsername(userName);
    }
}
