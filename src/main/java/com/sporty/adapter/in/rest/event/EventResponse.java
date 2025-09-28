package com.sporty.adapter.in.rest.event;

import com.sporty.adapter.out.f1api.Driver;
import io.micronaut.serde.annotation.Serdeable;

import java.time.OffsetDateTime;
import java.util.List;

@Serdeable
public record EventResponse(
        Integer sessionNumber,
        String countryName,
        String circuitName,
        OffsetDateTime dateEnd,
        OffsetDateTime dateStart,
        String location,
        String sessionName,
        String sessionType,
        int year,
        List<Driver> drivers
) {
}