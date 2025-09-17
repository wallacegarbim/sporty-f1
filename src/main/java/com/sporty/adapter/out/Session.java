package com.sporty.adapter.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import java.time.OffsetDateTime;

@Serdeable
public record Session(

        @JsonProperty("circuit_short_name") String circuitShortName,
        @JsonProperty("country_code") String countryCode,
        @JsonProperty("country_name") String countryName,
        @JsonProperty("date_end") OffsetDateTime dateEnd,
        @JsonProperty("date_start") OffsetDateTime dateStart,
        @JsonProperty("gmt_offset") String gmtOffset,
        String location,
        @JsonProperty("session_name") String sessionName,
        @JsonProperty("session_type") String sessionType,
        int year
) {

}
