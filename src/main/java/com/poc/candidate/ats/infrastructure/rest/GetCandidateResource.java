package com.poc.candidate.ats.infrastructure.rest;

import com.poc.candidate.ats.application.dtos.ListCandidatesResponse;
import com.poc.candidate.ats.domain.entities.Candidate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public interface GetCandidateResource extends CandidatesApi {

    @Operation(summary = "Listar candidatos", description = "Obtiene un listado de candidatos con filtros opcionales y paginación")
    @ApiResponse(responseCode = "200", description = "Listado de candidatos obtenido exitosamente", 
                 content = @Content(schema = @Schema(implementation = ListCandidatesResponse.class)))
    @GetMapping
    ResponseEntity<ListCandidatesResponse> listCandidates(
            @Parameter(description = "Filtro por nombre del candidateo") @RequestParam(required = false) String name,
            @Parameter(description = "Filtro por email del candidateo") @RequestParam(required = false) String email,
            @Parameter(description = "Filtro por género del candidateo") @RequestParam(required = false) String gender,
            @Parameter(description = "Filtro por ciudad del candidateo") @RequestParam(required = false) String city,
            @Parameter(description = "Filtro por estado del candidateo") @RequestParam(required = false) String state,
            @Parameter(description = "Filtro por país del candidateo") @RequestParam(required = false) String country,
            @Parameter(description = "Filtro por estado del candidateo") @RequestParam(required = false) String status,
            @Parameter(description = "Filtro por fecha de creación desde") @RequestParam(required = false) LocalDate createdFrom,
            @Parameter(description = "Filtro por fecha de creación hasta") @RequestParam(required = false) LocalDate createdTo,
            @Parameter(description = "Número de página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Obtener un candidateo", description = "Obtiene un candidateo específico por su ID")
    @ApiResponse(responseCode = "200", description = "Candidateo obtenido exitosamente", 
                 content = @Content(schema = @Schema(implementation = Candidate.class)))
    @ApiResponse(responseCode = "404", description = "Candidateo no encontrado")
    @GetMapping("/{id}")
    Candidate getCandidate(@Parameter(description = "ID del candidateo") @PathVariable Long id);
}
