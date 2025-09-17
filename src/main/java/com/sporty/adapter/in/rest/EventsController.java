package com.sporty.adapter.in.rest;

import com.sporty.application.port.in.EventQuery;
import com.sporty.domain.Event;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Function;

import java.util.Optional;

@Controller
public class EventsController {
    private final EventQuery eventQuery;

    public EventsController(final EventQuery eventQuery) {
        this.eventQuery = eventQuery;
    }

    @Get("/events")
    public Flowable<EventResponse> fetchEvents(
            @QueryValue("session_type") Optional<String> sessionType,
            @QueryValue("year") Optional<Integer> year,
            @QueryValue("country_name") Optional<String> countryName
    ) {

        return eventQuery
                .getEvents(
                        sessionType.orElse(null),
                        year.orElse(null),
                        countryName.orElse(null))
                .map(buildResponse());
    }

    private static Function<Event, EventResponse> buildResponse() {
        return r -> new EventResponse(
                r.countryName(),
                r.circuitName(),
                r.dateEnd(),
                r.dateStart(),
                r.location(),
                r.sessionName(),
                r.sessionType(),
                r.year()
        );
    }
}
