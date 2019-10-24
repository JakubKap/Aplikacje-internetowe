package pl.edu.wat.airline.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entity.Reservation;
import pl.edu.wat.airline.entity.User;

import java.util.List;

public interface ReservationRepo extends CrudRepository<Reservation, Long> {
    @Query("SELECT new pl.edu.wat.airline.entity.Reservation(r.id, r.reservationNo, r.isReservationPaid, r.isOnlineCheckInMade, r.numOfAdults, r.numOfInfants, r.numOfChildren, r.travelClass, r.reservationPrice, r.flight, r.user) " +
            "FROM Reservation r " +
            "WHERE r.user.id = :userId"
    )
    List<Reservation> findByUserId(@Param("userId") Long userId);
}

