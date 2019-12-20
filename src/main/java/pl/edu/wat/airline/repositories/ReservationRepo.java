package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends CrudRepository<Reservation, Long> {
    @Query("SELECT new pl.edu.wat.airline.entities.Reservation(r.id, r.reservationNo, r.isReservationPaid, r.isOnlineCheckInMade, r.numOfAdults, r.numOfInfants, r.numOfChildren, r.travelClass, r.reservationPrice, r.flight, r.userEntity) " +
            "FROM Reservation r " +
            "WHERE r.userEntity.login = :userLogin"
    )
    List<Reservation> findByUserLogin(@Param("userLogin") String userLogin);

    Optional<Reservation> findByReservationNo(@Param("reservationNo") String reservationNo);
}

