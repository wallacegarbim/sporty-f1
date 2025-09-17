package com.sporty.application.port.out;

import com.sporty.domain.Event;
import io.reactivex.rxjava3.core.Flowable;

public interface F1EventPort {

    Flowable<Event> getRaceSessions(String sessionType, Integer year, String countryName);
}
