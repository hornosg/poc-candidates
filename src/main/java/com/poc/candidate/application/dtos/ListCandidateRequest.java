package com.poc.candidate.application.dtos;

import java.time.LocalDate;

public record ListCandidateRequest(
        String name,
        String email,
        String gender,
        String city,
        String state,
        String country,
        String status,
        LocalDate createdFrom,
        LocalDate createdTo,
        int page,
        int size
) {
}