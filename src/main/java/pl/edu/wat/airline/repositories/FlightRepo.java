package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.Flight;

import java.time.LocalDateTime;


public interface FlightRepo extends CrudRepository<Flight, Long> {
    Flight findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(String flightNumber, LocalDateTime departureDatetime);
}
