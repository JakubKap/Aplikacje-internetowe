package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.FlightEntity;

import java.time.LocalDateTime;


public interface FlightsRepository extends CrudRepository<FlightEntity, Long> {
    FlightEntity findByFlightNumber(String flightNumber);

    FlightEntity findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(String flightNumber, LocalDateTime departureDatetime);
}
