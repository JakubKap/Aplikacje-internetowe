package pl.edu.wat.airline.services.interfaces;

import pl.edu.wat.airline.dtos.AirplaneDto;

public interface AirplanesService {

    Iterable<AirplaneDto> findAll();

    AirplaneDto findByName(String name);

    AirplaneDto addAirplane(AirplaneDto airplaneDto);

    AirplaneDto updateAirplane(AirplaneDto airplaneDto);
}
