package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.AvailableSitsEntity;

public interface AvailableSitsRepository extends CrudRepository<AvailableSitsEntity, Long> {

    @Query(
            "SELECT new pl.edu.wat.airline.entities.AvailableSitsEntity(" +
                    "a.flightId, " +
                    "a.airplaneName, a.seatClassCode, a.departureCode, a.arrivalCode, " +
                    "a.flightNumber, a.gateNumber, " +
                    "a.departureDateTime, a.arrivalDateTime, a.boardingDateTime, " +
                    "a.departureAirport, a.arrivalAirport, " +
                    "a.ekoAvailable, a.busAvailable, a.pierAvailable, " +
                    "a.businessClassAdultPrice, a.businessClassChildPrice, a.businessClassInfantPrice, " +
                    "a.economicClassAdultPrice, a.economicClassChildPrice, a.economicClassInfantPrice," +
                    "a.firstClassAdultPrice, a.firstClassChildPrice, a.firstClassInfantPrice, " +
                    "a.price) " +
                    "FROM AvailableSitsEntity a " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(departureDateTime,1,10),'%') " +
                    "AND a.departureAirport = :departureAirport " +
                    "AND a.arrivalAirport = :arrivalAirport " +
                    "AND a.busAvailable >= :classSeatsNum"
            )
    Iterable<AvailableSitsEntity> findAvailableBusSits(@Param("departureDateTime") String departureDateTime,
                                                       @Param("departureAirport") String departureAirport,
                                                       @Param("arrivalAirport") String arrivalAirport,
                                                       @Param("classSeatsNum") Integer classSeatsNum
    );

    @Query(
            "SELECT new pl.edu.wat.airline.entities.AvailableSitsEntity(" +
                    "a.id, " +
                    "a.airplaneName, a.seatClassCode, a.departureCode, a.arrivalCode, " +
                    "a.flightNumber, a.gateNumber, " +
                    "a.departureDateTime,  a.arrivalDateTime, a.boardingDateTime, " +
                    "a.departureAirport, a.arrivalAirport, " +
                    "a.ekoAvailable, a.busAvailable, a.pierAvailable, " +
                    "a.businessClassAdultPrice, a.businessClassChildPrice, a.businessClassInfantPrice, " +
                    "a.economicClassAdultPrice, a.economicClassChildPrice, a.economicClassInfantPrice," +
                    "a.firstClassAdultPrice, a.firstClassChildPrice, a.firstClassInfantPrice, a.price) " +
                    "FROM AvailableSitsEntity a " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(a.departureDateTime,1,10),'%')" +
                    "AND a.departureAirport = :departureAirport " +
                    "AND a.arrivalAirport = :arrivalAirport " +
                    "AND a.ekoAvailable >= :classSeatsNum"
    )
    Iterable<AvailableSitsEntity> findAvailableEkoSits(@Param("departureDateTime") String departureDateTime,
                                                       @Param("departureAirport")  String departureAirport,
                                                       @Param("arrivalAirport")    String arrivalAirport,
                                                       @Param("classSeatsNum")     Integer classSeatsNum
    );

    @Query(
            "SELECT new pl.edu.wat.airline.entities.AvailableSitsEntity(" +
                    "a.id, " +
                    "a.airplaneName, a.seatClassCode, a.departureCode, a.arrivalCode, " +
                    "a.flightNumber, a.gateNumber, " +
                    "a.departureDateTime,  a.arrivalDateTime, a.boardingDateTime, " +
                    "a.departureAirport, a.arrivalAirport, " +
                    "a.ekoAvailable, a.busAvailable, a.pierAvailable, " +
                    "a.businessClassAdultPrice, a.businessClassChildPrice, a.businessClassInfantPrice, " +
                    "a.economicClassAdultPrice, a.economicClassChildPrice, a.economicClassInfantPrice," +
                    "a.firstClassAdultPrice, a.firstClassChildPrice, a.firstClassInfantPrice, a.price) " +
                    "FROM AvailableSitsEntity a " +
                    "WHERE :departureDateTime LIKE CONCAT(SUBSTRING(a.departureDateTime,1,10),'%')" +
                    "AND a.departureAirport = :departureAirport " +
                    "AND a.arrivalAirport = :arrivalAirport " +
                    "AND a.pierAvailable >= :classSeatsNum"
    )
    Iterable<AvailableSitsEntity> findAvailablePierSits(@Param("departureDateTime") String departureDateTime,
                                                        @Param("departureAirport")  String departureAirport,
                                                        @Param("arrivalAirport")    String arrivalAirport,
                                                        @Param("classSeatsNum")     Integer classSeatsNum
    );
}
