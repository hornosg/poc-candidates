package com.poc.candidate.application.ports;

import com.poc.candidate.domain.entities.Candidate;

public interface GetCandidateUseCase {
    Candidate execute(String id);
}