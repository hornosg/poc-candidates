package com.poc.candidate.application.usescase;

import com.poc.candidate.application.dtos.CandidateRequest;
import com.poc.candidate.application.dtos.CandidateRequestMother;
import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateCandidateTest {

    @Mock
    private CandidateRepository repository;

    private CreateCandidate createCandidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createCandidate = new CreateCandidate(repository);
    }

    @Test
    void shouldCreateCandidateSuccessfully() {
        CandidateRequest request = CandidateRequestMother.random();
        Candidate expectedCandidate = buildCandidateEntity(request);
        when(repository.save(any(Candidate.class))).thenReturn(expectedCandidate);

        Candidate result = createCandidate.execute(request);

        assertNotNull(result);
        assertEquals(expectedCandidate.getName(), result.getName());
        assertEquals(expectedCandidate.getEmail(), result.getEmail());
        verify(repository, times(1)).save(any(Candidate.class));
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        CandidateRequest request = CandidateRequestMother.random();
        when(repository.save(any(Candidate.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> createCandidate.execute(request));
        verify(repository, times(1)).save(any(Candidate.class));
    }

    private static Candidate buildCandidateEntity(CandidateRequest request) {
        return new Candidate(
                UUID.randomUUID(),
                request.name(),
                request.email(),
                request.gender(),
                request.phone(),
                request.address(),
                request.city(),
                request.state(),
                request.country(),
                request.linkedin(),
                request.github(),
                request.salaryExpected(),
                request.status(),
                request.comments(),
                LocalDateTime.now()
        );
    }
}