package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.Airport;

public interface AirportRepo extends CrudRepository<Airport,Long> {
}
