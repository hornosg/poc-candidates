package com.poc.candidate.application.ports;

import com.poc.candidate.application.dtos.CandidateRequest;
import com.poc.candidate.domain.entities.Candidate;

public interface CreateCandidateUseCase {
    Candidate execute(CandidateRequest request);
}