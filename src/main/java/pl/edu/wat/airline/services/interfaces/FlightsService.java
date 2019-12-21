package pl.edu.wat.airline.services.interfaces;

import pl.edu.wat.airline.dtos.FlightDto;

import java.time.LocalDateTime;

public interface FlightsService {
    Iterable<FlightDto> findAll();

    FlightDto findByFlightNumber(String flightNumber);

    FlightDto findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(String flightNumber, LocalDateTime departureDatetime);

    FlightDto addFlight(FlightDto flightDto);

    FlightDto updateFlight(FlightDto flightDto);
}
