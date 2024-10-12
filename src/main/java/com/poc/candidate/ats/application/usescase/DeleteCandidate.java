package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.domain.entities.Candidate;
import com.poc.candidate.ats.domain.interfaces.CandidateRepository;
import com.poc.candidate.ats.application.ports.DeleteCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCandidate implements DeleteCandidateUseCase {

    private final CandidateRepository repository;

    @Override
    public void execute(Long id) {
        Candidate candidate = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        repository.delete(candidate);
    }
}