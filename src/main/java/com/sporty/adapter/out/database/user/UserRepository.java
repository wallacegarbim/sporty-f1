package com.sporty.adapter.out.database.user;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import io.reactivex.rxjava3.core.Maybe;
import org.reactivestreams.Publisher;

@MongoRepository
public interface UserRepository extends ReactiveStreamsCrudRepository<UserEntity, String> {

    Publisher<UserEntity> findByUserId(String userId);
}
