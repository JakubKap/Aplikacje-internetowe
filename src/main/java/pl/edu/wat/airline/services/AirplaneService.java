package pl.edu.wat.airline.services;

import pl.edu.wat.airline.dtos.AirplaneDto;

public interface AirplaneService {

    Iterable<AirplaneDto> findAll();

    AirplaneDto findByName(String name);

    AirplaneDto addAirplane(AirplaneDto airplaneDto);

    AirplaneDto updateAirplane(AirplaneDto airplaneDto);
}
