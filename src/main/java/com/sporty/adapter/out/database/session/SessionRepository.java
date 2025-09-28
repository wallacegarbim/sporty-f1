package com.sporty.adapter.out.database.session;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import io.reactivex.rxjava3.core.Flowable;

@MongoRepository
public interface SessionRepository extends ReactiveStreamsCrudRepository<SessionEntity, String> {

    Flowable<SessionEntity> findBySessionType(String sessionType);
    Flowable<SessionEntity> findByYear(Integer year);
    Flowable<SessionEntity> findByCountryName(String countryName);

    Flowable<SessionEntity> findBySessionTypeAndYear(String sessionType, Integer year);
    Flowable<SessionEntity> findBySessionTypeAndCountryName(String sessionType, String countryName);
    Flowable<SessionEntity> findByYearAndCountryName(Integer year, String countryName);

    Flowable<SessionEntity> findBySessionTypeAndYearAndCountryName(String sessionType, Integer year, String countryName);

}
