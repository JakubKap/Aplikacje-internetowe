package pl.edu.wat.airline.services.interfaces;

import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.dtos.AvailableSitsDto;
import pl.edu.wat.airline.entities.AvailableSitsEntity;

public interface AvailableSitsService {

    Iterable<AvailableSitsDto> findAll();

    Iterable<AvailableSitsDto> findAvailableBusSits(@Param("departureDateTime") String departureDateTime,
                                                       @Param("departureAirport")  String departureAirport,
                                                       @Param("arrivalAirport")    String arrivalAirport,
                                                       @Param("classSeatsNum")     Integer classSeatsNum
    );

    Iterable<AvailableSitsDto> findAvailableEkoSits(@Param("departureDateTime") String departureDateTime,
                                                       @Param("departureAirport")  String departureAirport,
                                                       @Param("arrivalAirport")    String arrivalAirport,
                                                       @Param("classSeatsNum")     Integer classSeatsNum
    );

    Iterable<AvailableSitsDto> findAvailablePierSits(@Param("departureDateTime") String departureDateTime,
                                                        @Param("departureAirport")  String departureAirport,
                                                        @Param("arrivalAirport")    String arrivalAirport,
                                                        @Param("classSeatsNum")     Integer classSeatsNum
    );
}
