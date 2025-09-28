package com.sporty.adapter.out.database.bet;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

@MongoRepository
public interface BetRepository extends ReactiveStreamsCrudRepository<BetEntity, String> {

    Flowable<BetEntity> findBySessionKeyAndDriverNumber(Integer sessionKey, Integer driverNumber);

    Flowable<BetEntity> findBySessionKey(Integer sessionId);
}
