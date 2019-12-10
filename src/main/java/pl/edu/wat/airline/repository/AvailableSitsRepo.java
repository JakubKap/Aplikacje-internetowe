package pl.edu.wat.airline.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entity.AvailableSits;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AvailableSitsRepo extends CrudRepository<AvailableSits, Long> {

    @Query(
            "SELECT new pl.edu.wat.airline.entity.AvailableSits(" +
                    "flightId, airplaneId, seatClassId, departureId, arrivalId, " +
                    "departureDateTime, " +
//                    "arrivalDateTime, " +
                    "departureAirport, arrivalAirport, " +
                    "busAvailable, ekoAvailable, pierAvailable, " +
                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice) " +
                    "FROM AvailableSits " +
//                    "WHERE departureDateTime = :departureDateTime " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
//                    "AND arrivalDateTime LIKE CONCAT(SUBSTRING(:arrivalDateTime,1,5),'%')" +
                    "AND departureAirport = :departureAirport " +
                    "AND arrivalAirport = :arrivalAirport " +
                    "AND busAvailable >= :busSeats " +
                    "AND ekoAvailable >= :ekoSeats " +
                    "AND pierAvailable >= :pierSeats"
    )
    Iterable<AvailableSits> findAvailableSits(@Param("departureDateTime") String departureDateTime,
//                                              @Param("arrivalDateTime") String arrivalDateTime,
                                              @Param("departureAirport") String departureAirport,
                                              @Param("arrivalAirport") String arrivalAirport,
                                              @Param("busSeats") Integer busSeats,
                                              @Param("ekoSeats") Integer ekoSeats,
                                              @Param("pierSeats") Integer pierSeats
                                              );
//    AvailableSits findByDepartureDateTimeAndArrivalDateTimeAndDepartureAirportAndArrivalAirportAndEkoAvailableIsGreaterThanEqualAndBusAvailableIsGreaterThanEqualAndPierAvailableIsGreaterThanEqual(LocalDateTime departureDateTime,
//                                                                                                                                                                                               LocalDateTime arrivalDateTime,
//                                                                                                                                                                                               String departureAirport,
//                                                                                                                                                                                               String arrivalAirport,
//                                                                                                                                                                                               Integer eko,
//                                                                                                                                                                                               Integer bus,
//                                                                                                                                                                                               Integer pier
//    );
}
