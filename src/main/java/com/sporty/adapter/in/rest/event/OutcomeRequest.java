package com.sporty.adapter.in.rest.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record OutcomeRequest(
        @NotNull
        @JsonProperty("driver_id") Integer driverId
) {
}
