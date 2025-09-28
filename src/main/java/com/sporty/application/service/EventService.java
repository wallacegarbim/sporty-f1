package com.sporty.application.service;

import com.sporty.adapter.out.database.bet.BetEntity;
import com.sporty.adapter.out.database.bet.BetRepository;
import com.sporty.adapter.out.database.user.UserEntity;
import com.sporty.adapter.out.database.user.UserRepository;
import com.sporty.application.port.in.EventCommand;
import com.sporty.application.port.in.EventQuery;
import com.sporty.application.port.out.F1EventPort;
import com.sporty.domain.Event;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import jakarta.inject.Singleton;

import java.math.BigDecimal;

@Singleton
public class EventService implements EventQuery, EventCommand {

    private final F1EventPort f1EventPort;
    private final UserRepository userRepository;
    private final BetRepository betRepository;

    public EventService(final F1EventPort f1EventPort, final UserRepository userRepository, final BetRepository betRepository) {
        this.f1EventPort = f1EventPort;
        this.userRepository = userRepository;
        this.betRepository = betRepository;
    }

    @Override
    public Flowable<Event> getEvents(final String sessionType, final Integer year, final String countryName) {
        return f1EventPort.getRaceSessions(sessionType, year, countryName);
    }


    @Override
    public Completable eventOutcome(final Integer sessionId, final Integer driverId) {

        Flowable<BetEntity> winningBets = betRepository.findBySessionKeyAndDriverNumber(sessionId, driverId);

        Completable winners = winningBets.flatMapCompletable(bet -> {
            BigDecimal payout = bet.amount().multiply(BigDecimal.valueOf(bet.odds())); // assuming odds are stored

            BetEntity updatedBet = new BetEntity(
                    bet.id(),
                    bet.userId(),
                    bet.sessionKey(),
                    bet.driverNumber(),
                    bet.amount(),
                    BetEntity.Status.WON,
                    bet.odds()
            );

            Completable updateUser = Maybe.fromPublisher(userRepository.findByUserId(bet.userId()))
                    .flatMapCompletable(user -> {
                        UserEntity credited = new UserEntity(
                                user.id(),
                                user.userId(),
                                user.balance().add(payout)
                        );
                        return Completable.fromPublisher(userRepository.update(credited));
                    });

            Completable updateBet = Completable.fromPublisher(betRepository.update(updatedBet));

            return Completable.mergeArray(updateUser, updateBet);
        });

        Flowable<BetEntity> losingBets = betRepository.findBySessionKey(sessionId)
                .filter(b -> !b.driverNumber().equals(driverId));

        Completable losers = losingBets.flatMapCompletable(bet -> {
            BetEntity updatedBet = new BetEntity(
                    bet.id(),
                    bet.userId(),
                    bet.sessionKey(),
                    bet.driverNumber(),
                    bet.amount(),
                    BetEntity.Status.LOST,
                    bet.odds()
            );
            return Completable.fromPublisher(betRepository.update(updatedBet));
        });

        return Completable.mergeArray(winners, losers);
    }

}
