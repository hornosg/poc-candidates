package com.poc.candidate.application.dtos;

import net.datafaker.Faker;

import java.time.LocalDateTime;

public class CandidateRequestMother {
    static Faker faker = new Faker();

    public static CandidateRequest random() {
        return new CandidateRequest(
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
            "Experienced software developer"
        );
    }
}