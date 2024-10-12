package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.domain.entities.Candidate;
import com.poc.candidate.ats.domain.interfaces.CandidateRepository;
import com.poc.candidate.ats.application.ports.GetCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCandidate implements GetCandidateUseCase {
    private final CandidateRepository repository;

    @Override
    public Candidate execute(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Candidato no encontrado"));
    }
}
