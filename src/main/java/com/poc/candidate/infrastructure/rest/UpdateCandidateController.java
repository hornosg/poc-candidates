package com.poc.candidate.infrastructure.rest;

import com.poc.candidate.application.dtos.CandidateRequest;
import com.poc.candidate.application.ports.UpdateCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class UpdateCandidateController implements UpdateCandidateResource {
    private final UpdateCandidateUseCase updateCandidateUseCase;

    @Override
    public ResponseEntity<HttpStatus> updateCandidate(String id, CandidateRequest request) {
        updateCandidateUseCase.execute(id, request);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}