package com.poc.candidate.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeleteCandidateResource extends CandidatesApi {

    @Operation(
        summary = "Delete a candidate",
        description = "Deletes a candidate with the specified ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Candidate deleted successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Candidate not found"
            )
        }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCandidate(@PathVariable String id);
}