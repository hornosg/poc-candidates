package com.poc.candidate.application.usescase;

import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import com.poc.candidate.application.ports.DeleteCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCandidate implements DeleteCandidateUseCase {

    private final CandidateRepository repository;

    @Override
    public void execute(String id) {
        Candidate candidate = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        repository.delete(candidate);
    }
}