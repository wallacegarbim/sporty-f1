package com.sporty.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
public record Bet(
        String userId,
        Integer sessionId,
        Integer driverId,
        BigDecimal amount,
        String status,
        Integer odds
) {
}
