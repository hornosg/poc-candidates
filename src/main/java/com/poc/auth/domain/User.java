package com.poc.auth.domain;

import lombok.Data;

@Data
public class User {
    private String id;
    private String email;
    private String password;
}
