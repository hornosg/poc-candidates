package com.poc.candidate.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidate {
    private UUID id;
    private String name;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String linkedin;
    private String github;
    private String salaryExpected;
    private String status;
    private String comments;
    private LocalDateTime createdAt;
}


