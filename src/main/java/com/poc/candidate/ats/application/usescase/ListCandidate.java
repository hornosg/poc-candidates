package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.application.dtos.ListCandidateRequest;
import com.poc.candidate.ats.application.dtos.ListCandidatesResponse;
import com.poc.candidate.ats.domain.entities.Candidate;
import com.poc.candidate.ats.domain.interfaces.CandidateRepository;
import com.poc.candidate.ats.application.ports.ListCandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCandidate implements ListCandidateUseCase {
    private final CandidateRepository repository;

    @Override
    public ListCandidatesResponse execute(ListCandidateRequest request) {
        Page<Candidate> candidatePage = repository.findAll(request);

        return new ListCandidatesResponse(
            candidatePage.stream().toList(),
            candidatePage.getNumber(),
            candidatePage.getTotalPages(),
            candidatePage.getTotalElements(),
            candidatePage.getSize()
        );
    }
}
