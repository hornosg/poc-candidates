package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.application.usescase.DeleteCandidate;
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

class DeleteCandidateTest {

    @Mock
    private CandidateRepository repository;

    private DeleteCandidate deleteCandidate;
    static Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteCandidate = new DeleteCandidate(repository);
    }

    @Test
    void shouldDeleteCandidateSuccessfully() {
        Long candidateId = faker.number().randomNumber();
        Candidate candidate = new Candidate(); // Crear un candidato dummy
        when(repository.findById(candidateId)).thenReturn(Optional.of(candidate));
        doNothing().when(repository).delete(candidate);
        
        assertDoesNotThrow(() -> deleteCandidate.execute(candidateId));

        verify(repository, times(1)).findById(candidateId);
        verify(repository, times(1)).delete(candidate);
    }

    @Test
    void shouldThrowExceptionWhenCandidateNotFound() {
        Long candidateId = faker.number().randomNumber();
        when(repository.findById(candidateId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> deleteCandidate.execute(candidateId));
        verify(repository, times(1)).findById(candidateId);
        verify(repository, never()).delete(any(Candidate.class));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        Long candidateId = faker.number().randomNumber();
        Candidate candidate = new Candidate(); // Crear un candidato dummy
        when(repository.findById(candidateId)).thenReturn(Optional.of(candidate));
        doThrow(new RuntimeException("Database error")).when(repository).delete(candidate);

        assertThrows(RuntimeException.class, () -> deleteCandidate.execute(candidateId));
        verify(repository, times(1)).findById(candidateId);
        verify(repository, times(1)).delete(candidate);
    }
}