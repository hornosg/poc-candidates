package com.poc.candidate.application.usescase;

import com.poc.candidate.application.dtos.CandidateRequest;
import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import com.poc.candidate.application.ports.CreateCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCandidate implements CreateCandidateUseCase {
    private final CandidateRepository repository;

    @Override
    public Candidate execute(CandidateRequest request) {
        Candidate candidate = new Candidate(
                UUID.randomUUID(),
                request.name(),
                request.email(),
                request.gender(),
                request.phone(),
                request.address(),
                request.city(),
                request.state(),
                request.country(),
                request.linkedin(),
                request.github(),
                request.salaryExpected(),
                request.status(),
                request.comments(),
                LocalDateTime.now()
        );

        return repository.save(candidate);
    }
}