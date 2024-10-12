package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.application.usescase.GetCandidate;
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

class GetCandidateTest {

    @Mock
    private CandidateRepository repository;

    private GetCandidate getCandidate;
    static Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getCandidate = new GetCandidate(repository);
    }

    @Test
    void shouldGetCandidateSuccessfully() {
        Long candidateId = faker.number().randomNumber();
        Candidate expectedCandidate = new Candidate(); // Crear un candidato dummy
        expectedCandidate.setId(candidateId);
        when(repository.findById(candidateId)).thenReturn(Optional.of(expectedCandidate));
        
        Candidate result = getCandidate.execute(candidateId);
        
        assertNotNull(result);
        assertEquals(candidateId, result.getId());
        verify(repository, times(1)).findById(candidateId);
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        Long candidateId = faker.number().randomNumber();
        when(repository.findById(candidateId)).thenReturn(Optional.empty());
        
        assertThrows(IllegalArgumentException.class, () -> getCandidate.execute(candidateId));
        verify(repository, times(1)).findById(candidateId);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        Long candidateId = faker.number().randomNumber();
        when(repository.findById(candidateId)).thenThrow(new RuntimeException("Database error"));
        
        assertThrows(RuntimeException.class, () -> getCandidate.execute(candidateId));
        verify(repository, times(1)).findById(candidateId);
    }
}