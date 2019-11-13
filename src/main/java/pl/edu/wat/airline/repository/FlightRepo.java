package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.Flight;

import java.time.LocalDateTime;


public interface FlightRepo extends CrudRepository<Flight, Long> {
    Flight findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(String flightNumber, LocalDateTime departureDatetime);
}
