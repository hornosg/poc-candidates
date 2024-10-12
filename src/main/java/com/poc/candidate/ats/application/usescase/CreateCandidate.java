package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.application.dtos.CandidateRequest;
import com.poc.candidate.ats.domain.entities.Candidate;
import com.poc.candidate.ats.domain.interfaces.CandidateRepository;
import com.poc.candidate.ats.application.ports.CreateCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateCandidate implements CreateCandidateUseCase {
    private final CandidateRepository repository;

    @Override
    public Candidate execute(CandidateRequest request) {
        Candidate candidate = new Candidate(
                null,
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