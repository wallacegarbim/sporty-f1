package com.sporty.domain;

import java.time.OffsetDateTime;

public record Event(
        String countryName,
        String circuitName,
        OffsetDateTime dateEnd,
        OffsetDateTime dateStart,
        String location,
        String sessionName,
        String sessionType,
        int year
) {

}