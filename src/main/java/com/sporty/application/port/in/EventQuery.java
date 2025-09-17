package com.sporty.application.port.in;

import com.sporty.domain.Event;
import io.reactivex.rxjava3.core.Flowable;

public interface EventQuery {

    Flowable<Event> getEvents(final String sessionType, final Integer year, final String countryName);
}
