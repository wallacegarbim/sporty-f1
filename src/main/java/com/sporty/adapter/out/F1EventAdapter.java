package com.sporty.adapter.out;

import com.sporty.application.port.out.F1EventPort;
import com.sporty.domain.Event;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;

@Singleton
public class F1EventAdapter implements F1EventPort {

    private final F1SessionClient f1SessionClient;

    public F1EventAdapter(final F1SessionClient f1SessionClient) {
        this.f1SessionClient = f1SessionClient;
    }

    @Override
    public Flowable<Event> getRaceSessions(final String sessionType, final Integer year, final String countryName) {
        return f1SessionClient.getRaceSessions(sessionType, year, countryName).map(F1EventAdapter::buildEvent);
    }

    private static Event buildEvent(final Session s) {
        return new Event(
                s.countryName(),
                s.circuitShortName(),
                s.dateEnd(),
                s.dateStart(),
                s.location(),
                s.sessionName(),
                s.sessionType(),
                s.year()
        );
    }
}
