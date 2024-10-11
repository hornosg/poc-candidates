package com.poc.candidate.application.usescase;

import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteCandidateTest {

    @Mock
    private CandidateRepository repository;

    private DeleteCandidate deleteCandidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteCandidate = new DeleteCandidate(repository);
    }

    @Test
    void shouldDeleteCandidateSuccessfully() {
        // Arrange
        String candidateId = "123e4567-e89b-12d3-a456-426614174000";
        Candidate candidate = new Candidate(); // Crear un candidato dummy
        when(repository.findById(candidateId)).thenReturn(Optional.of(candidate));
        doNothing().when(repository).delete(candidate);

        // Act
        assertDoesNotThrow(() -> deleteCandidate.execute(candidateId));

        // Assert
        verify(repository, times(1)).findById(candidateId);
        verify(repository, times(1)).delete(candidate);
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Arrange
        String candidateId = "123e4567-e89b-12d3-a456-426614174000";
        when(repository.findById(candidateId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> deleteCandidate.execute(candidateId));
        verify(repository, times(1)).findById(candidateId);
        verify(repository, never()).delete(any(Candidate.class));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        String candidateId = "123e4567-e89b-12d3-a456-426614174000";
        Candidate candidate = new Candidate(); // Crear un candidato dummy
        when(repository.findById(candidateId)).thenReturn(Optional.of(candidate));
        doThrow(new RuntimeException("Database error")).when(repository).delete(candidate);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> deleteCandidate.execute(candidateId));
        verify(repository, times(1)).findById(candidateId);
        verify(repository, times(1)).delete(candidate);
    }
}