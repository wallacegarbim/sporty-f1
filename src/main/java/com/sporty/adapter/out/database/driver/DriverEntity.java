package com.sporty.adapter.out.database.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity("drivers")
public record DriverEntity(

        @Id
        @GeneratedValue
        String id,

        @JsonProperty("full_name") String fullName,
        @JsonProperty("driver_number") Integer driverNumber,
        @JsonProperty("session_key") Integer sessionKey,
        int odds
) { }