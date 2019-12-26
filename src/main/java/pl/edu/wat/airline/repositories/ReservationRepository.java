package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
    @Query("SELECT new pl.edu.wat.airline.entities.ReservationEntity(r.id, r.reservationNo, r.isReservationPaid, r.isOnlineCheckInMade, r.numOfAdults, r.numOfInfants, r.numOfChildren, r.travelClass, r.reservationPrice, r.flightEntity, r.userEntity) " +
            "FROM ReservationEntity r " +
            "WHERE r.userEntity.login = :userLogin"
    )
    Iterable<ReservationEntity> findByUserLogin(@Param("userLogin") String userLogin);

    @Query("SELECT new pl.edu.wat.airline.entities.ReservationEntity(r.id, r.reservationNo, r.isReservationPaid, r.isOnlineCheckInMade, r.numOfAdults, r.numOfInfants, r.numOfChildren, r.travelClass, r.reservationPrice, r.flightEntity, r.userEntity) " +
            "FROM ReservationEntity r " +
            "WHERE r.reservationNo = :reservationNo"
    )
    ReservationEntity findByReservationNo(@Param("reservationNo") String reservationNo);
}
