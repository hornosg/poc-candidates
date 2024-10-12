package com.poc.candidate.iam.infrastructure.repository;

import com.poc.candidate.iam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
