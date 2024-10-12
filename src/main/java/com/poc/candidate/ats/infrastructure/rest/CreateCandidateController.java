package com.poc.candidate.ats.infrastructure.rest;

import com.poc.candidate.ats.application.dtos.CandidateRequest;
import com.poc.candidate.ats.application.ports.CreateCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CreateCandidateController implements CreateCandidateResource {
    private final CreateCandidateUseCase createCandidateUseCase;

    @Override
    public ResponseEntity<HttpStatus> createCandidate(CandidateRequest request) {
        createCandidateUseCase.execute(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}