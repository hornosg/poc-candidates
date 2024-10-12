package com.poc.candidate.ats.application.ports;

import com.poc.candidate.ats.domain.entities.Candidate;

public interface GetCandidateUseCase {
    Candidate execute(Long id);
}