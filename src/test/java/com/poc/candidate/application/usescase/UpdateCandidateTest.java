package com.poc.candidate.application.usescase;

import com.poc.candidate.application.dtos.CandidateRequest;
import com.poc.candidate.application.dtos.CandidateRequestMother;
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

class UpdateCandidateTest {

    @Mock
    private CandidateRepository repository;

    private UpdateCandidate updateCandidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateCandidate = new UpdateCandidate(repository);
    }

    @Test
    void shouldUpdateCandidateSuccessfully() {
        // Arrange
        UUID candidateId = UUID.randomUUID();
        CandidateRequest request = CandidateRequestMother.random();
        Candidate existingCandidate = new Candidate();
        existingCandidate.setId(candidateId);
        when(repository.findById(candidateId.toString())).thenReturn(Optional.of(existingCandidate));
        when(repository.save(any(Candidate.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Candidate updatedCandidate = updateCandidate.execute(candidateId.toString(), request);

        // Assert
        assertNotNull(updatedCandidate);
        assertEquals(candidateId, updatedCandidate.getId());
        assertEquals(request.name(), updatedCandidate.getName());
        assertEquals(request.email(), updatedCandidate.getEmail());
        // Add more assertions for other fields
        verify(repository, times(1)).findById(candidateId.toString());
        verify(repository, times(1)).save(any(Candidate.class));
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        // Arrange
        UUID candidateId = UUID.randomUUID();
        CandidateRequest request = CandidateRequestMother.random();
        when(repository.findById(candidateId.toString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> updateCandidate.execute(candidateId.toString(), request));
        verify(repository, times(1)).findById(candidateId.toString());
        verify(repository, never()).save(any(Candidate.class));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        UUID candidateId = UUID.randomUUID();
        CandidateRequest request = CandidateRequestMother.random();
        Candidate existingCandidate = new Candidate();
        existingCandidate.setId(candidateId);
        when(repository.findById(candidateId.toString())).thenReturn(Optional.of(existingCandidate));
        when(repository.save(any(Candidate.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> updateCandidate.execute(candidateId.toString(), request));
        verify(repository, times(1)).findById(candidateId.toString());
        verify(repository, times(1)).save(any(Candidate.class));
    }
}