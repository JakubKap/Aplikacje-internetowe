package pl.edu.wat.airline.services.interfaces;

import pl.edu.wat.airline.dtos.AirportDto;

public interface AirportsService {
    Iterable<AirportDto> findAll();

    AirportDto findByIata(String iata);

    AirportDto addAirport(AirportDto airportDto);

    AirportDto updateAirportName(AirportDto airportDto);
}
