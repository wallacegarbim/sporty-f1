package com.sporty.adapter.in.rest.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sporty.adapter.out.f1api.Driver;
import io.micronaut.serde.annotation.Serdeable;

import java.time.OffsetDateTime;
import java.util.List;

@Serdeable
public record EventResponse(
        @JsonProperty("session_number")
        Integer sessionNumber,
        @JsonProperty("country_name")
        String countryName,
        @JsonProperty("circuit_name")
        String circuitName,
        @JsonProperty("date_end")
        OffsetDateTime dateEnd,
        @JsonProperty("date_start")
        OffsetDateTime dateStart,

        String location,

        @JsonProperty("session_name")
        String sessionName,

        @JsonProperty("session_type")
        String sessionType,

        int year,
        List<Driver> drivers
) {
}