package com.poc.candidate.ats.infrastructure.rest;

import com.poc.candidate.ats.application.dtos.CandidateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreateCandidateResource extends CandidatesApi {

    @Operation(
        summary = "Create a new candidate",
        description = "Creates a new candidate with the provided details",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Candidate created successfully"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input"
            )
        }
    )
    @PostMapping
    ResponseEntity<HttpStatus> createCandidate(@RequestBody CandidateRequest request);
}