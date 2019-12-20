package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.Airport;

public interface AirportRepo extends CrudRepository<Airport,Long> {
}
