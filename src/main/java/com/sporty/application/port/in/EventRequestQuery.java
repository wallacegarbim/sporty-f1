package com.sporty.application.port.in;

public record EventRequestQuery(
        String session,
        String year,
        String country
) {
}
