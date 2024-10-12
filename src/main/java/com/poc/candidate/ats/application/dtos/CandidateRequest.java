package com.poc.candidate.ats.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record CandidateRequest(
        String name,
        String email,
        String gender,
        String phone,
        String address,
        String city,
        String state,
        String country,
        String linkedin,
        String github,
        String salaryExpected,
        String status,
        String comments
) {}