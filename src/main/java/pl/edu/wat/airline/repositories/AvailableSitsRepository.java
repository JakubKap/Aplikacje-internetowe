package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.AvailableSitsEntity;

public interface AvailableSitsRepository extends CrudRepository<AvailableSitsEntity, Long> {

//    @Query(
//            "SELECT new pl.edu.wat.airline.entities.AvailableSitsEntity(" +
//                    "flightId, airplaneId, seatClassId, departureId, arrivalId, " +
//                    "flightNumber, gateNumber, " +
//                    "departureDateTime, arrivalDateTime, boardingDateTime, " +
//                    "departureAirport, arrivalAirport, " +
//                    "busAvailable, ekoAvailable, pierAvailable, " +
//                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
//                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
//                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice, price) " +
//                    "FROM AvailableSitsEntity " +
//                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
//                    "AND departureAirport = :departureAirport " +
//                    "AND arrivalAirport = :arrivalAirport " +
//                    "AND busAvailable >= :busSeats " +
//                    "AND ekoAvailable >= :ekoSeats " +
//                    "AND pierAvailable >= :pierSeats"
//    )
//    Iterable<AvailableSitsEntity> findAvailableSits(@Param("departureDateTime") String departureDateTime,
//                                                    @Param("departureAirport") String departureAirport,
//                                                    @Param("arrivalAirport") String arrivalAirport,
//                                                    @Param("busSeats") Integer busSeats,
//                                                    @Param("ekoSeats") Integer ekoSeats,
//                                                    @Param("pierSeats") Integer pierSeats
//                                              );
//
    @Query(
            "SELECT new pl.edu.wat.airline.entities.AvailableSitsEntity(" +
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
                    "FROM AvailableSitsEntity " +
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
    Iterable<AvailableSitsEntity> findAvailableBusSits(@Param("departureDateTime") String departureDateTime,
                                                       @Param("departureAirport")  String departureAirport,
                                                       @Param("arrivalAirport")    String arrivalAirport,
                                                       @Param("classSeatsNum")     Integer classSeatsNum
    );

    @Query(
            "SELECT new pl.edu.wat.airline.entities.AvailableSitsEntity(" +
                    "flightId, airplaneId, seatClassId, departureId, arrivalId, " +
                    "flightNumber, gateNumber, " +
                    "departureDateTime,  arrivalDateTime, boardingDateTime, " +
                    "departureAirport, arrivalAirport, " +
                    "busAvailable, ekoAvailable, pierAvailable, " +
                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice, price) " +
                    "FROM AvailableSitsEntity " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
                    "AND departureAirport = :departureAirport " +
                    "AND arrivalAirport = :arrivalAirport " +
                    "AND ekoAvailable >= :classSeatsNum"
    )
    Iterable<AvailableSitsEntity> findAvailableEkoSits(@Param("departureDateTime") String departureDateTime,
                                                       @Param("departureAirport")  String departureAirport,
                                                       @Param("arrivalAirport")    String arrivalAirport,
                                                       @Param("classSeatsNum")     Integer classSeatsNum
    );

    @Query(
            "SELECT new pl.edu.wat.airline.entities.AvailableSitsEntity(" +
                    "flightId, airplaneId, seatClassId, departureId, arrivalId, " +
                    "flightNumber, gateNumber, " +
                    "departureDateTime,  arrivalDateTime, boardingDateTime, " +
                    "departureAirport, arrivalAirport, " +
                    "busAvailable, ekoAvailable, pierAvailable, " +
                    "businessClassAdultPrice, businessClassChildPrice, businessClassInfantPrice, " +
                    "economicClassAdultPrice, economicClassChildPrice, economicClassInfantPrice," +
                    "firstClassAdultPrice, firstClassChildPrice, firstClassInfantPrice, price) " +
                    "FROM AvailableSitsEntity " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%')" +
                    "AND departureAirport = :departureAirport " +
                    "AND arrivalAirport = :arrivalAirport " +
                    "AND pierAvailable >= :classSeatsNum"
    )
    Iterable<AvailableSitsEntity> findAvailablePierSits(@Param("departureDateTime") String departureDateTime,
                                                        @Param("departureAirport")  String departureAirport,
                                                        @Param("arrivalAirport")    String arrivalAirport,
                                                        @Param("classSeatsNum")     Integer classSeatsNum
    );
}
