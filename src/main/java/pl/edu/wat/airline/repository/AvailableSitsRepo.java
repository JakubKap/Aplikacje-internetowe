package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.AvailableSits;

import java.time.LocalDateTime;

public interface AvailableSitsRepo extends CrudRepository<AvailableSits, Long> {
    AvailableSits findByDepartureDateTimeAndArrivalDateTimeAndDepartureAirportAndArrivalAirportAndEkoAvailableIsGreaterThanEqualAndBusAvailableIsGreaterThanEqualAndPierAvailableIsGreaterThanEqual(LocalDateTime departureDateTime,
                                                                                                                                                                                               LocalDateTime arrivalDateTime,
                                                                                                                                                                                               String departureAirport,
                                                                                                                                                                                               String arrivalAirport,
                                                                                                                                                                                               Integer eko,
                                                                                                                                                                                               Integer bus,
                                                                                                                                                                                               Integer pier
    );
}
