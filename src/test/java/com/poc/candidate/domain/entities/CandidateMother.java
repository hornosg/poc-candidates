package com.poc.candidate.domain.entities;

import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.util.UUID;

public class CandidateMother {
    static Faker faker = new Faker();

    public static Candidate random() {
        return new Candidate(
            UUID.randomUUID(),
            faker.name().fullName(),
            faker.internet().emailAddress(),
            null,
            faker.phoneNumber().cellPhone(),
            faker.address().fullAddress(),
            faker.address().city(),
            faker.address().state(),
            faker.address().country(),
            faker.internet().url(),
            faker.internet().url(),
            faker.expression("#{numerify '######'}"),
            null,
            null,
            LocalDateTime.now()
        );
    }
}