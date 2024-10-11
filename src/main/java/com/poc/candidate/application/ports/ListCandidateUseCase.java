package com.poc.candidate.application.ports;

import com.poc.candidate.application.dtos.ListCandidateRequest;
import com.poc.candidate.application.dtos.ListCandidatesResponse;

public interface ListCandidateUseCase {
    ListCandidatesResponse execute(ListCandidateRequest request);
}