package com.poc.candidate.ats.application.ports;

import com.poc.candidate.ats.application.dtos.ListCandidateRequest;
import com.poc.candidate.ats.application.dtos.ListCandidatesResponse;

public interface ListCandidateUseCase {
    ListCandidatesResponse execute(ListCandidateRequest request);
}