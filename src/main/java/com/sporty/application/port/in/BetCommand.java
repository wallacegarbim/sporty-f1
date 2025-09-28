package com.sporty.application.port.in;

import com.sporty.domain.Bet;
import io.reactivex.rxjava3.core.Single;

import java.math.BigDecimal;

public interface BetCommand {
    Single<Bet> placeBet(String userId, Integer sessionKey, Integer driverNumber, String amount);
}
