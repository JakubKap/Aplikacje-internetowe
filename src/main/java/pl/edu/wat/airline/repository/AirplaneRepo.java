package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.Airplane;

public interface AirplaneRepo extends CrudRepository<Airplane, Long> {
}
