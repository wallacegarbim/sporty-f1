package com.sporty.adapter.out.database.driver;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.annotation.Nullable;
import org.reactivestreams.Publisher;


@MongoRepository
public interface DriverRepository extends ReactiveStreamsCrudRepository<DriverEntity, String> {

    Flowable<DriverEntity> findBySessionKey(@Nullable Integer sessionKey);

    Publisher<DriverEntity> findBySessionKeyAndDriverNumber(Integer sessionKey, Integer driverNumber);
}
