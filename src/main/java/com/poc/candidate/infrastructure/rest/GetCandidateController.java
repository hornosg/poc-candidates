package com.poc.candidate.infrastructure.rest;

import com.poc.candidate.application.dtos.ListCandidateRequest;
import com.poc.candidate.application.dtos.ListCandidatesResponse;
import com.poc.candidate.application.ports.GetCandidateUseCase;
import com.poc.candidate.application.ports.ListCandidateUseCase;
import com.poc.candidate.domain.entities.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class GetCandidateController implements GetCandidateResource {
    private final GetCandidateUseCase getCandidateUseCase;
    private final ListCandidateUseCase listCandidateUseCase;

    @GetMapping
    public ResponseEntity<ListCandidatesResponse> listCandidates(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate createdFrom,
            @RequestParam(required = false) LocalDate createdTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        ListCandidateRequest request = new ListCandidateRequest(name, email, gender, city, state, country,
                status, createdFrom, createdTo, page, size);
        ListCandidatesResponse response = listCandidateUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Candidate getCandidate(@PathVariable String id) {
        return getCandidateUseCase.execute(id);
    }
}
