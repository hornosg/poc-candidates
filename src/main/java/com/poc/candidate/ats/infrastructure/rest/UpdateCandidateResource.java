package com.poc.candidate.ats.infrastructure.rest;

import com.poc.candidate.ats.application.dtos.CandidateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdateCandidateResource extends CandidatesApi{

    @Operation(
        summary = "Update an existing candidate",
        description = "Updates an existing candidate with the provided details",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Candidate updated successfully"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid input"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Candidate not found"
            )
        }
    )
    @PutMapping("/{id}")
    ResponseEntity<HttpStatus> updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest request);
}