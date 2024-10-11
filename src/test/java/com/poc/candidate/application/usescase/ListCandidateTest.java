package com.poc.candidate.application.usescase;

import com.poc.candidate.application.dtos.ListCandidateRequest;
import com.poc.candidate.application.dtos.ListCandidatesResponse;
import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListCandidateTest {

    @Mock
    private CandidateRepository repository;

    private ListCandidate listCandidate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listCandidate = new ListCandidate(repository);
    }

    @Test
    void shouldListCandidatesSuccessfully() {
        // Arrange
        ListCandidateRequest request = new ListCandidateRequest(
            null, null, null, null, null, null, null, null, null, 0, 10
        );
        List<Candidate> candidates = Arrays.asList(
            createCandidate("John Doe"),
            createCandidate("Jane Smith")
        );
        Page<Candidate> candidatePage = new PageImpl<>(candidates, PageRequest.of(0, 10), 2);
        when(repository.findAll(request)).thenReturn(candidatePage);

        // Act
        ListCandidatesResponse response = listCandidate.execute(request);

        // Assert
        assertNotNull(response);
        assertEquals(2, response.candidates().size());
        assertEquals(0, response.currentPage());
        assertEquals(1, response.totalPages());
        assertEquals(2, response.totalElements());
        assertEquals(10, response.size());
        verify(repository, times(1)).findAll(request);
    }

    @Test
    void shouldReturnEmptyListWhenNoCandidates() {
        // Arrange
        ListCandidateRequest request = new ListCandidateRequest(
            null, null, null, null, null, null, null, null, null, 0, 10
        );
        Page<Candidate> emptyPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        when(repository.findAll(request)).thenReturn(emptyPage);

        // Act
        ListCandidatesResponse response = listCandidate.execute(request);

        // Assert
        assertNotNull(response);
        assertTrue(response.candidates().isEmpty());
        assertEquals(0, response.currentPage());
        assertEquals(0, response.totalPages());
        assertEquals(0, response.totalElements());
        assertEquals(10, response.size());
        verify(repository, times(1)).findAll(request);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        ListCandidateRequest request = new ListCandidateRequest(
            null, null, null, null, null, null, null, null, null, 0, 10
        );
        when(repository.findAll(request)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> listCandidate.execute(request));
        verify(repository, times(1)).findAll(request);
    }

    private Candidate createCandidate(String name) {
        Candidate candidate = new Candidate();
        candidate.setId(UUID.randomUUID());
        candidate.setName(name);
        // Set other properties as needed
        return candidate;
    }
}