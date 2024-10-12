package com.poc.candidate.ats.application.dtos;

import com.poc.candidate.ats.domain.entities.Candidate;

import java.util.List;

public record ListCandidatesResponse(
    List<Candidate> candidates,
    int currentPage,
    int totalPages,
    long totalElements,
    int size
) {
}