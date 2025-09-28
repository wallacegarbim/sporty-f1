package com.sporty.adapter.out;

import com.sporty.adapter.out.database.driver.DriverEntity;
import com.sporty.adapter.out.database.driver.DriverRepository;
import com.sporty.adapter.out.database.session.SessionEntity;
import com.sporty.adapter.out.database.session.SessionRepository;
import com.sporty.adapter.out.f1api.Driver;
import com.sporty.application.port.out.F1EventPort;
import com.sporty.domain.Event;
import io.reactivex.rxjava3.core.Flowable;
import jakarta.inject.Singleton;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.concurrent.ThreadLocalRandom.current;

@Singleton
public class F1EventAdapter implements F1EventPort {
    private final SessionRepository sessionRepository;
    private final DriverRepository driverRepository;
    private final Random random = new SecureRandom();

    public F1EventAdapter(final SessionRepository sessionRepository, final DriverRepository driverRepository) {
        this.sessionRepository = sessionRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Flowable<Event> getRaceSessions( String sessionType, Integer year, String countryName) {

        Flowable<SessionEntity> sessions;

        if (sessionType == null && year == null && countryName == null) {
            sessions = Flowable.fromPublisher(sessionRepository.findAll());
        } else {
            sessions = Flowable.defer(() -> {
                if (sessionType != null && year != null && countryName != null) {
                    return sessionRepository.findBySessionTypeAndYearAndCountryName(sessionType, year, countryName);
                }
                if (sessionType != null && year != null) {
                    return sessionRepository.findBySessionTypeAndYear(sessionType, year);
                }
                if (sessionType != null && countryName != null) {
                    return sessionRepository.findBySessionTypeAndCountryName(sessionType, countryName);
                }
                if (year != null && countryName != null) {
                    return sessionRepository.findByYearAndCountryName(year, countryName);
                }
                if (sessionType != null) {
                    return sessionRepository.findBySessionType(sessionType);
                }
                if (year != null) {
                    return sessionRepository.findByYear(year);
                }
                return sessionRepository.findByCountryName(countryName);
            });
        }

        return sessions.flatMap(session ->
                driverRepository.findBySessionKey(session.sessionKey())
                        .toList()
                        .map(drivers -> buildEvent(session, drivers))
                        .toFlowable()
        );
    }

    private static Event buildEvent(final SessionEntity s, final List<DriverEntity> d) {
        return new Event(
                s.sessionKey(),
                s.countryName(),
                s.circuitShortName(),
                s.dateEnd(),
                s.dateStart(),
                s.location(),
                s.sessionName(),
                s.sessionType(),
                s.year(),
                buildDriver(d)
        );
    }

    private static List<Driver> buildDriver(final List<DriverEntity> d) {
        return d.stream().map(driver -> new Driver(
                driver.fullName(),
                driver.driverNumber(),
                getOdd()
        )).collect(Collectors.toList());
    }

    private static int getOdd() {
        int[] options = {2, 3, 4};
        return options[current().nextInt(options.length)];
    }

}
