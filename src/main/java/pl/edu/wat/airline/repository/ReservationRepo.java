package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.Reservation;

public interface ReservationRepo extends CrudRepository<Reservation, Long> {
}
