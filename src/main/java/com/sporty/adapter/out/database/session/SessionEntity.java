package com.sporty.adapter.out.database.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.time.OffsetDateTime;

@Serdeable
@MappedEntity("sessions")
public record SessionEntity(

        @Id
        @GeneratedValue
        String id,

        @JsonProperty("circuit_key") int circuitKey,
        @JsonProperty("circuit_short_name") String circuitShortName,
        @JsonProperty("country_code") String countryCode,
        @JsonProperty("country_key") int countryKey,
        @JsonProperty("country_name") String countryName,
        @JsonProperty("date_end") OffsetDateTime dateEnd,
        @JsonProperty("date_start") OffsetDateTime dateStart,
        @JsonProperty("gmt_offset") String gmtOffset,
        String location,
        @JsonProperty("meeting_key") int meetingKey,
        @JsonProperty("session_key") int sessionKey,
        @JsonProperty("session_name") String sessionName,
        @JsonProperty("session_type") String sessionType,
        int year
) {}

