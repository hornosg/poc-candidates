package com.poc.candidate.application.usescase;

import com.poc.candidate.application.dtos.CandidateRequest;
import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import com.poc.candidate.application.ports.UpdateCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCandidate implements UpdateCandidateUseCase {
    private final CandidateRepository repository;

    @Override
    public Candidate execute(String id, CandidateRequest request) {
        Candidate existingCandidate = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        Candidate updatedCandidate = updateCandidate(request, existingCandidate);
        return repository.save(updatedCandidate);
    }

    private Candidate updateCandidate(CandidateRequest request, Candidate existingCandidate) {
        if (request.name() != null && !request.name().isEmpty()) {
            existingCandidate.setName(request.name());
        }
        if (request.email() != null && !request.email().isEmpty()) {
            existingCandidate.setEmail(request.email());
        }
        if (request.gender() != null && !request.gender().isEmpty()) {
            existingCandidate.setGender(request.gender());
        }
        if (request.phone() != null && !request.phone().isEmpty()) {
            existingCandidate.setPhone(request.phone());
        }
        if (request.address() != null && !request.address().isEmpty()) {
            existingCandidate.setAddress(request.address());
        }
        if (request.city() != null && !request.city().isEmpty()) {
            existingCandidate.setCity(request.city());
        }
        if (request.state() != null && !request.state().isEmpty()) {
            existingCandidate.setState(request.state());
        }
        if (request.country() != null && !request.country().isEmpty()) {
            existingCandidate.setCountry(request.country());
        }
        if (request.linkedin() != null && !request.linkedin().isEmpty()) {
            existingCandidate.setLinkedin(request.linkedin());
        }
        if (request.github() != null && !request.github().isEmpty()) {
            existingCandidate.setGithub(request.github());
        }
        if (request.salaryExpected() != null && !request.salaryExpected().isEmpty()) {
            existingCandidate.setSalaryExpected(request.salaryExpected());
        }
        if (request.status() != null && !request.status().isEmpty()) {
            existingCandidate.setStatus(request.status());
        }
        if (request.comments() != null && !request.comments().isEmpty()) {
            existingCandidate.setComments(request.comments());
        }

        return existingCandidate;
    }
}