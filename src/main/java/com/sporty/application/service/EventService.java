package com.sporty.application.service;

import com.sporty.adapter.out.F1SessionClient;
import com.sporty.application.port.in.EventQuery;
import com.sporty.application.port.out.F1EventPort;
import com.sporty.domain.Event;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;

@Singleton
public class EventService implements EventQuery {

    private final F1EventPort f1EventPort;

    public EventService(final F1SessionClient f1SessionClient, final F1EventPort f1EventPort) {

        this.f1EventPort = f1EventPort;
    }

    @Override
    public Flowable<Event> getEvents(final String sessionType, final Integer year, final String countryName) {
        return f1EventPort.getRaceSessions(sessionType, year, countryName);
    }


}
