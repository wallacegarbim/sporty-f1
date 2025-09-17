package com.sporty.adapter.in.rest;

import io.micronaut.serde.annotation.Serdeable;

import java.time.OffsetDateTime;

@Serdeable
public record EventResponse(
        String countryName,
        String circuitName,
        OffsetDateTime dateEnd,
        OffsetDateTime dateStart,
        String location,
        String sessionName,
        String sessionType,
        int year
) {
}
