package com.sporty.adapter.out.f1api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import java.time.OffsetDateTime;

@Serdeable
public record Session(

        @JsonProperty("circuit_key") String circuitKey,
        @JsonProperty("circuit_short_name") String circuitShortName,
        @JsonProperty("country_code") String countryCode,
        @JsonProperty("country_key") String countryKey,
        @JsonProperty("country_name") String countryName,
        @JsonProperty("date_end") OffsetDateTime dateEnd,
        @JsonProperty("date_start") OffsetDateTime dateStart,
        @JsonProperty("gmt_offset") String gmtOffset,
        String location,
        @JsonProperty("meeting_key") String meetingKey,
        @JsonProperty("session_key") String sessionKey,
        @JsonProperty("session_name") String sessionName,
        @JsonProperty("session_type") String sessionType,
        int year
) { }
