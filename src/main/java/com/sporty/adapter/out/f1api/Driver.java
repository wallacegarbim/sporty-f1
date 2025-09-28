package com.sporty.adapter.out.f1api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Driver(
        @JsonProperty("full_name") String fullName,
        @JsonProperty("driver_number") Integer driverNumber,
        Integer odd
) { }