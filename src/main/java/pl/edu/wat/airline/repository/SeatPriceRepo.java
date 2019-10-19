package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.SeatPrice;

public interface SeatPriceRepo extends CrudRepository<SeatPrice, Long> {
}
