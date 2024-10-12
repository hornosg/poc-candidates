package com.poc.candidate.ats.application.ports;

import com.poc.candidate.ats.application.dtos.CandidateRequest;
import com.poc.candidate.ats.domain.entities.Candidate;

public interface UpdateCandidateUseCase {
    Candidate execute(Long id, CandidateRequest request);
}