package com.sporty.adapter.in.rest.bet;

import com.sporty.application.port.in.BetCommand;
import com.sporty.domain.Bet;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.rxjava3.core.Single;
import jakarta.validation.Valid;

@Controller
public class BetController {

    private final BetCommand betCommand;

    public BetController(final BetCommand betCommand) {
        this.betCommand = betCommand;
    }

    @Post("/bets")
    public Single<Bet> placeBet(@Body @Valid BetRequest betRequest) {
        return betCommand.placeBet(betRequest.userId(), betRequest.sessionKey(), betRequest.driverNumber(), betRequest.amount());
    }
}
