package com.poc.candidate.ats.infrastructure.rest;

import com.poc.candidate.ats.application.ports.DeleteCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class DeleteCandidateController implements DeleteCandidateResource {
    private final DeleteCandidateUseCase deleteCandidateUseCase;

    @Override
    public ResponseEntity<Void> deleteCandidate(Long id) {
        deleteCandidateUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}