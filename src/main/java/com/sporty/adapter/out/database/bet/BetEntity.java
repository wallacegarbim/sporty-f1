package com.sporty.adapter.out.database.bet;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Serdeable
@MappedEntity("bets")
public record BetEntity(

        @Id
        @GeneratedValue
        String id,

        @JsonProperty("user_id") String userId,
        @JsonProperty("session_key") int sessionKey,
        @JsonProperty("driver_number") Integer driverNumber,
        BigDecimal amount,
        Status status,
        Integer odds
) {
        public enum Status { PENDING, WON, LOST }
}

