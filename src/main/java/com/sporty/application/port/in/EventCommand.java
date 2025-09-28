package com.sporty.application.port.in;

import io.reactivex.rxjava3.core.Completable;

public interface EventCommand {
    Completable eventOutcome(Integer sessionId, Integer driverId);
}
