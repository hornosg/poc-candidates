package com.poc.candidate.application.usescase;

import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import com.poc.candidate.application.ports.GetCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCandidate implements GetCandidateUseCase {
    private final CandidateRepository repository;

    @Override
    public Candidate execute(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Candidato no encontrado"));
    }
}
