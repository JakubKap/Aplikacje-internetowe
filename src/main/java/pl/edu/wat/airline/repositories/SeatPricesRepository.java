package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.SeatPriceEntity;

public interface SeatPricesRepository extends CrudRepository<SeatPriceEntity, Long> {
}
