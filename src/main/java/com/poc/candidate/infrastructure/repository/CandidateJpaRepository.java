package com.poc.candidate.infrastructure.repository;

import com.poc.candidate.application.dtos.ListCandidateRequest;
import com.poc.candidate.domain.entities.Candidate;
import com.poc.candidate.domain.interfaces.CandidateRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CandidateJpaRepository extends JpaRepository<Candidate, String>, JpaSpecificationExecutor<Candidate>, CandidateRepository {

    @Override
    default Page<Candidate> findAll(ListCandidateRequest request) {
        Pageable pageable = PageRequest.of(request.page(), request.size());

        Specification<Candidate> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            applyFilters(request, root, criteriaBuilder, predicates);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return findAll(spec, pageable);
    }

    private static void applyFilters(ListCandidateRequest request, Root<Candidate> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (request.name() != null && !request.name().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.name().toLowerCase() + "%"));
        }

        if (request.email() != null && !request.email().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + request.email().toLowerCase() + "%"));
        }

        if (request.gender() != null && !request.gender().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("gender"), request.gender()));
        }

        if (request.city() != null && !request.city().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + request.city().toLowerCase() + "%"));
        }

        if (request.state() != null && !request.state().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("state")), "%" + request.state().toLowerCase() + "%"));
        }

        if (request.country() != null && !request.country().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("country")), "%" + request.country().toLowerCase() + "%"));
        }

        if (request.status() != null && !request.status().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("status"), request.status()));
        }

        if (request.createdFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), request.createdFrom()));
        }

        if (request.createdTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), request.createdTo()));
        }
    }
}