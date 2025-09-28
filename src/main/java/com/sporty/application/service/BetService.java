package com.sporty.application.service;

import com.sporty.adapter.out.database.bet.BetEntity;
import com.sporty.adapter.out.database.bet.BetRepository;
import com.sporty.adapter.out.database.driver.DriverRepository;
import com.sporty.adapter.out.database.user.UserEntity;
import com.sporty.adapter.out.database.user.UserRepository;
import com.sporty.application.port.in.BetCommand;
import com.sporty.domain.Bet;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import jakarta.inject.Singleton;

import java.math.BigDecimal;

import static com.sporty.adapter.out.database.bet.BetEntity.Status.PENDING;

@Singleton
public class BetService implements BetCommand {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    public BetService(final BetRepository betRepository, final UserRepository userRepository, final DriverRepository driverRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Single<Bet> placeBet(final String userId,
                                final Integer sessionKey,
                                final Integer driverNumber,
                                final String amount) {

        BigDecimal betAmount = new BigDecimal(amount);

        return Maybe.fromPublisher(userRepository.findByUserId(userId))
                .switchIfEmpty(
                        Maybe.fromPublisher(
                                userRepository.save(new UserEntity(null, userId, BigDecimal.valueOf(100)))
                        )
                )
                .flatMap(user -> {
                    if (user.balance().compareTo(betAmount) < 0) {
                        return Maybe.error(new IllegalStateException("Insufficient balance"));
                    }

                    UserEntity updatedUser = new UserEntity(
                            user.id(),
                            user.userId(),
                            user.balance().subtract(betAmount)
                    );

                    return Maybe.fromPublisher(userRepository.update(updatedUser))
                            .flatMap(savedUser ->
                                    Maybe.fromPublisher(driverRepository.findBySessionKeyAndDriverNumber(sessionKey, driverNumber))
                                            .flatMap(driver -> {
                                                BetEntity betEntity = new BetEntity(
                                                        null,
                                                        savedUser.userId(),
                                                        sessionKey,
                                                        driverNumber,
                                                        betAmount,
                                                        PENDING,
                                                        driver.odds()
                                                );

                                                return Maybe.fromPublisher(betRepository.save(betEntity))
                                                        .map(savedBetEntity ->
                                                                new Bet(
                                                                        savedBetEntity.userId(),
                                                                        savedBetEntity.sessionKey(),
                                                                        savedBetEntity.driverNumber(),
                                                                        savedBetEntity.amount(),
                                                                        savedBetEntity.status().toString(),
                                                                        savedBetEntity.odds()
                                                                )
                                                        );
                                            })
                            );
                })
                .toSingle();
    }
}

