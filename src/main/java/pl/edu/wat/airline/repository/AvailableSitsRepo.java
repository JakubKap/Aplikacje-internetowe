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
                    "flightNumber, gateNumber, " +
                    "departureDateTime, arrivalDateTime, boardingDateTime, " +
                    "departureAirport, arrivalAirport, " +
                    "busAvailable, ekoAvailable, pierAvailable, " +
                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice, price) " +
                    "FROM AvailableSits " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
                    "AND departureAirport = :departureAirport " +
                    "AND arrivalAirport = :arrivalAirport " +
                    "AND busAvailable >= :busSeats " +
                    "AND ekoAvailable >= :ekoSeats " +
                    "AND pierAvailable >= :pierSeats"
    )
    Iterable<AvailableSits> findAvailableSits(@Param("departureDateTime") String departureDateTime,
                                              @Param("departureAirport") String departureAirport,
                                              @Param("arrivalAirport") String arrivalAirport,
                                              @Param("busSeats") Integer busSeats,
                                              @Param("ekoSeats") Integer ekoSeats,
                                              @Param("pierSeats") Integer pierSeats
                                              );

    @Query(
            "SELECT new pl.edu.wat.airline.entity.AvailableSits(" +
                    "flightId, airplaneId, seatClassId, departureId, arrivalId, " +
                    "flightNumber, gateNumber, " +
                    "departureDateTime,  arrivalDateTime, boardingDateTime, " +
                    "departureAirport, arrivalAirport, " +
                    "busAvailable, ekoAvailable, pierAvailable, " +
//                    "CASE " +
//                    "WHEN :classSeats = 'Ekonomiczna' THEN ekoAvailable " +
//                    "WHEN :classSeats = 'Biznes' THEN busAvailable " +
//                    "WHEN :classSeats = 'Pierwsza' THEN pierAvailable " +
//                    "ELSE 0 END, " +
                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice, price) " +
                    "FROM AvailableSits " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
                    "AND departureAirport = :departureAirport " +
                    "AND arrivalAirport = :arrivalAirport " +
                    "AND busAvailable >= :classSeatsNum"
//                    "AND " +
//                    "CASE " +
//                    "WHEN :classSeats = 'Ekonomiczna' THEN ekoAvailable " +
//                    "WHEN :classSeats = 'Biznes' THEN busAvailable " +
//                    "WHEN :classSeats = 'Pierwsza' THEN pierAvailable " +
//                    "ELSE 0 END >= :classSeatsNum"
    )
    Iterable<AvailableSits> findAvailableBusSits( @Param("departureDateTime") String departureDateTime,
                                                    @Param("departureAirport")  String departureAirport,
                                                    @Param("arrivalAirport")    String arrivalAirport,
                                                    @Param("classSeatsNum")     Integer classSeatsNum
    );

    @Query(
            "SELECT new pl.edu.wat.airline.entity.AvailableSits(" +
                    "flightId, airplaneId, seatClassId, departureId, arrivalId, " +
                    "flightNumber, gateNumber, " +
                    "departureDateTime,  arrivalDateTime, boardingDateTime, " +
                    "departureAirport, arrivalAirport, " +
                    "busAvailable, ekoAvailable, pierAvailable, " +
                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice, price) " +
                    "FROM AvailableSits " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
                    "AND departureAirport = :departureAirport " +
                    "AND arrivalAirport = :arrivalAirport " +
                    "AND ekoAvailable >= :classSeatsNum"
    )
    Iterable<AvailableSits> findAvailableEkoSits( @Param("departureDateTime") String departureDateTime,
                                                  @Param("departureAirport")  String departureAirport,
                                                  @Param("arrivalAirport")    String arrivalAirport,
                                                  @Param("classSeatsNum")     Integer classSeatsNum
    );

    @Query(
            "SELECT new pl.edu.wat.airline.entity.AvailableSits(" +
                    "flightId, airplaneId, seatClassId, departureId, arrivalId, " +
                    "flightNumber, gateNumber, " +
                    "departureDateTime,  arrivalDateTime, boardingDateTime, " +
                    "departureAirport, arrivalAirport, " +
                    "busAvailable, ekoAvailable, pierAvailable, " +
                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice, price) " +
                    "FROM AvailableSits " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
                    "AND departureAirport = :departureAirport " +
                    "AND arrivalAirport = :arrivalAirport " +
                    "AND pierAvailable >= :classSeatsNum"
    )
    Iterable<AvailableSits> findAvailablePierSits( @Param("departureDateTime") String departureDateTime,
                                                  @Param("departureAirport")  String departureAirport,
                                                  @Param("arrivalAirport")    String arrivalAirport,
                                                  @Param("classSeatsNum")     Integer classSeatsNum
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
