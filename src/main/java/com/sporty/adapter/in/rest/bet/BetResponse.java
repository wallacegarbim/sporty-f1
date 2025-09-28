package com.sporty.adapter.in.rest.bet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
public record BetResponse(
        @JsonProperty("user_id")
        String userId,
        @JsonProperty("session_id")
        Integer sessionId,
        @JsonProperty("driver_id")
        Integer driverId,
        BigDecimal amount,
        String status,
        Integer odds
) {
}