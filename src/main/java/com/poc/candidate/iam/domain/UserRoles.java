package com.poc.candidate.iam.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    SUPER_ADMIN,
    ADMIN,
    RECRUITER;

    @Override
    public String getAuthority() {
        return name();
    }
}
