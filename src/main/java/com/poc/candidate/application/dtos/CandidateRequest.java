package com.poc.candidate.application.dtos;

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