package com.sporty.configuration;

import com.sporty.adapter.out.database.driver.DriverEntity;
import com.sporty.adapter.out.database.driver.DriverRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.ThreadLocalRandom.current;

@Requires(notEnv = "test")
@Singleton
public class DriverOddsInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DriverOddsInitializer.class);

    private final DriverRepository driverRepository;

    public DriverOddsInitializer(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @EventListener
    public void onStartup(ServerStartupEvent event) {
        LOG.info("Assigning random odds to all drivers...");

        Flowable.fromPublisher(driverRepository.findAll())
                .flatMap(driver -> {
                    int randomOdds = randomOdds();
                    DriverEntity updated = new DriverEntity(
                            driver.id(),
                            driver.fullName(),
                            driver.driverNumber(),
                            driver.sessionKey(),
                            randomOdds
                    );
                    return Flowable.fromPublisher(driverRepository.update(updated));
                })
                .doOnComplete(() -> LOG.info("All drivers updated with random odds"))
                .blockingSubscribe(
                        d -> LOG.info("Updated driver {} with odds {}", d.fullName(), d.odds()),
                        e -> LOG.error("Error updating drivers with odds", e)
                );
    }

    private int randomOdds() {
        int[] odds = {2, 3, 4};
        return odds[current().nextInt(odds.length)];
    }
}

