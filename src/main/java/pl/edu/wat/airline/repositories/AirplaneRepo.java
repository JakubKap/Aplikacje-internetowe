package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.Airplane;

public interface AirplaneRepo extends CrudRepository<Airplane, Long> {
}
