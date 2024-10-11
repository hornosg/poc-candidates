package com.poc.candidate.application.ports;

import com.poc.candidate.application.dtos.CandidateRequest;
import com.poc.candidate.domain.entities.Candidate;

public interface UpdateCandidateUseCase {
    Candidate execute(String id, CandidateRequest request);
}