package com.sporty.domain;

import com.sporty.adapter.out.f1api.Driver;

import java.time.OffsetDateTime;
import java.util.List;

public record Event(
        Integer sessionId,
        String countryName,
        String circuitName,
        OffsetDateTime dateEnd,
        OffsetDateTime dateStart,
        String location,
        String sessionName,
        String sessionType,
        int year,
        List<Driver> drivers
) {

}