package com.poc.candidate.ats.application.ports;

import com.poc.candidate.ats.application.dtos.CandidateRequest;
import com.poc.candidate.ats.domain.entities.Candidate;

public interface CreateCandidateUseCase {
    Candidate execute(CandidateRequest request);
}