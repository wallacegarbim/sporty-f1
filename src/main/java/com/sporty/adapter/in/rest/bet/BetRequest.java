package com.sporty.adapter.in.rest.bet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Serdeable
public record BetRequest(
        @NotBlank
        @JsonProperty("user_id") String userId,

        @NotNull
        @JsonProperty("session_id") Integer sessionKey,

        @NotNull
        @JsonProperty("driver_id") Integer driverNumber,

        @NotNull
        @Positive
        String amount
) {
}
