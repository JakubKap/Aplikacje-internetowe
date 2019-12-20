package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.AirplaneEntity;

public interface AirplanesRepository extends CrudRepository<AirplaneEntity, Long> {
    AirplaneEntity findByName(String name);
}
