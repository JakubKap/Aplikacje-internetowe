package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.Flight;

public interface FlightRepo extends CrudRepository<Flight, Long> {
}
