package pl.edu.wat.airline.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.airline.entity.Reservation;
import pl.edu.wat.airline.entity.User;

import java.util.List;

public interface ReservationRepo extends CrudRepository<Reservation, Long> {
}

