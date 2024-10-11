package com.poc.candidate.domain.interfaces;

import com.poc.candidate.application.dtos.ListCandidateRequest;
import com.poc.candidate.domain.entities.Candidate;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CandidateRepository {
    Optional<Candidate> findById(String id);
    Candidate save(Candidate candidate);
    void delete(Candidate candidate);
    Page<Candidate> findAll(ListCandidateRequest request);
}
