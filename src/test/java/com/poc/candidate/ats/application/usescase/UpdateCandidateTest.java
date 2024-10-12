package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.application.dtos.CandidateRequest;
import com.poc.candidate.ats.application.dtos.CandidateRequestMother;
import com.poc.candidate.ats.domain.entities.Candidate;
import com.poc.candidate.ats.domain.interfaces.CandidateRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCandidateTest {

    @Mock
    private CandidateRepository repository;

    private UpdateCandidate updateCandidate;
    static Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateCandidate = new UpdateCandidate(repository);
    }

    @Test
    void shouldUpdateCandidateSuccessfully() {
        CandidateRequest request = CandidateRequestMother.random();
        Candidate existingCandidate = new Candidate();
        Long candidateId = faker.number().randomNumber();
        existingCandidate.setId(candidateId);
        when(repository.findById(candidateId)).thenReturn(Optional.of(existingCandidate));
        when(repository.save(any(Candidate.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Candidate updatedCandidate = updateCandidate.execute(candidateId, request);

        assertNotNull(updatedCandidate);
        assertEquals(candidateId, updatedCandidate.getId());
        assertEquals(request.name(), updatedCandidate.getName());
        assertEquals(request.email(), updatedCandidate.getEmail());
        verify(repository, times(1)).findById(candidateId);
        verify(repository, times(1)).save(any(Candidate.class));
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        Long candidateId = faker.number().randomNumber();
        CandidateRequest request = CandidateRequestMother.random();
        when(repository.findById(candidateId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> updateCandidate.execute(candidateId, request));
        verify(repository, times(1)).findById(candidateId);
        verify(repository, never()).save(any(Candidate.class));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        Long candidateId = faker.number().randomNumber();
        CandidateRequest request = CandidateRequestMother.random();
        Candidate existingCandidate = new Candidate();
        existingCandidate.setId(candidateId);
        when(repository.findById(candidateId)).thenReturn(Optional.of(existingCandidate));
        when(repository.save(any(Candidate.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> updateCandidate.execute(candidateId, request));
        verify(repository, times(1)).findById(candidateId);
        verify(repository, times(1)).save(any(Candidate.class));
    }
}