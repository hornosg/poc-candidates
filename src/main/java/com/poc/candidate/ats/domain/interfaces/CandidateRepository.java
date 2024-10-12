package com.poc.candidate.ats.domain.interfaces;

import com.poc.candidate.ats.application.dtos.ListCandidateRequest;
import com.poc.candidate.ats.domain.entities.Candidate;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CandidateRepository {
    Optional<Candidate> findById(Long id);
    Candidate save(Candidate candidate);
    void delete(Candidate candidate);
    Page<Candidate> findAll(ListCandidateRequest request);
}
