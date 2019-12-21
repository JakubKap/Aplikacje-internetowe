package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.AirportEntity;

public interface AirportsRepository extends CrudRepository<AirportEntity,Long> {
    AirportEntity findByIata(String iata);
}
