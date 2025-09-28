package com.sporty.adapter.out.database.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Serdeable
@MappedEntity("users")
public record UserEntity(

        @Id
        @GeneratedValue
        String id,

        @JsonProperty("user_id") String userId,
        BigDecimal balance
) {}

