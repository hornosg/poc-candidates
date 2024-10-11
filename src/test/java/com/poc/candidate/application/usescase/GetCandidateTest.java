package com.poc.candidate.application.usescase;

import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCandidateTest {

    @Mock
    private CandidateRepository repository;

    private GetCandidate getCandidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getCandidate = new GetCandidate(repository);
    }

    @Test
    void shouldGetCandidateSuccessfully() {
        // Arrange
        UUID candidateId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Candidate expectedCandidate = new Candidate(); // Crear un candidato dummy
        expectedCandidate.setId(candidateId);
        when(repository.findById(candidateId.toString())).thenReturn(Optional.of(expectedCandidate));

        // Act
        Candidate result = getCandidate.execute(candidateId.toString());

        // Assert
        assertNotNull(result);
        assertEquals(candidateId, result.getId());
        verify(repository, times(1)).findById(candidateId.toString());
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Arrange
        UUID candidateId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        when(repository.findById(candidateId.toString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> getCandidate.execute(candidateId.toString()));
        verify(repository, times(1)).findById(candidateId.toString());
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        UUID candidateId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        when(repository.findById(candidateId.toString())).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> getCandidate.execute(candidateId.toString()));
        verify(repository, times(1)).findById(candidateId.toString());
    }
}