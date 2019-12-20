package pl.edu.wat.airline.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entities.SeatPrice;

public interface SeatPriceRepo extends CrudRepository<SeatPrice, Long> {
}
