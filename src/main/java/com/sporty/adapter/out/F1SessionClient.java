package com.sporty.adapter.out;


import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.rxjava3.core.Flowable;

@Client("https://api.openf1.org/v1")
public interface F1SessionClient {

    @Get("/sessions")
    Flowable<Session> getRaceSessions(
            @QueryValue("session_type") @Nullable String sessionType,
            @QueryValue("year") @Nullable Integer year,
            @QueryValue("country_name") @Nullable String countryName
    );
}
