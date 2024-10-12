package com.poc.candidate.ats.application.usescase;

import com.poc.candidate.ats.application.dtos.ListCandidateRequest;
import com.poc.candidate.ats.application.dtos.ListCandidatesResponse;
import com.poc.candidate.ats.application.usescase.ListCandidate;
import com.poc.candidate.ats.domain.entities.Candidate;
import com.poc.candidate.ats.domain.interfaces.CandidateRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListCandidateTest {

    @Mock
    private CandidateRepository repository;

    private ListCandidate listCandidate;
    static Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listCandidate = new ListCandidate(repository);
    }

    @Test
    void shouldListCandidatesSuccessfully() {
        
        ListCandidateRequest request = new ListCandidateRequest(
            null, null, null, null, null, null, null, null, null, 0, 10
        );
        List<Candidate> candidates = Arrays.asList(
            createCandidate("John Doe"),
            createCandidate("Jane Smith")
        );
        Page<Candidate> candidatePage = new PageImpl<>(candidates, PageRequest.of(0, 10), 2);
        when(repository.findAll(request)).thenReturn(candidatePage);

        
        ListCandidatesResponse response = listCandidate.execute(request);

        
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
        
        ListCandidateRequest request = new ListCandidateRequest(
            null, null, null, null, null, null, null, null, null, 0, 10
        );
        Page<Candidate> emptyPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        when(repository.findAll(request)).thenReturn(emptyPage);

        
        ListCandidatesResponse response = listCandidate.execute(request);

        
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
        
        ListCandidateRequest request = new ListCandidateRequest(
            null, null, null, null, null, null, null, null, null, 0, 10
        );
        when(repository.findAll(request)).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> listCandidate.execute(request));
        verify(repository, times(1)).findAll(request);
    }

    private Candidate createCandidate(String name) {
        Candidate candidate = new Candidate();
        candidate.setId(faker.number().randomNumber());
        candidate.setName(name);
        // Set other properties as needed
        return candidate;
    }
}