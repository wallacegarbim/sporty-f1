package com.sporty.adapter.in.rest.event;

import com.sporty.application.port.in.EventCommand;
import com.sporty.application.port.in.EventQuery;
import com.sporty.domain.Event;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Function;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Controller
public class EventsController {
    private final EventQuery eventQuery;
    private final EventCommand eventCommand;

    public EventsController(final EventQuery eventQuery, final EventCommand eventCommand) {
        this.eventQuery = eventQuery;
        this.eventCommand = eventCommand;
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

    @Post("/events/{sessionId}/outcome")
    public Completable fetchEventOutcome(
            @PathVariable @NotNull Integer sessionId,
            @Body OutcomeRequest outcomeRequest
    ) {
        return eventCommand.eventOutcome(sessionId, outcomeRequest.driverId());
    }

    private static Function<Event, EventResponse> buildResponse() {
        return r -> new EventResponse(
                r.sessionId(),
                r.countryName(),
                r.circuitName(),
                r.dateEnd(),
                r.dateStart(),
                r.location(),
                r.sessionName(),
                r.sessionType(),
                r.year(),
                r.drivers()
        );
    }
}
