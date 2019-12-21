package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.dtos.AirportDto;
import pl.edu.wat.airline.services.AirportsServiceImpl;

@RestController
@RequestMapping("/api/airports")

public class AirportController {

    private AirportsServiceImpl airportService;

    @Autowired
    public AirportController(AirportsServiceImpl airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/all_airports")
    public Iterable<AirportDto> getAllAirports(){
        return airportService.findAll();
    }

    @GetMapping
    public AirportDto getByIata(@RequestParam String iata) {
        return airportService.findByIata(iata);
    }

    @PostMapping
    public AirportDto addAirport(@RequestBody AirportDto airportDto) {
        return airportService.addAirport(airportDto);
    }

    @PutMapping
    public AirportDto updateAirportName(@RequestBody AirportDto airportDto) {
        return airportService.updateAirportName(airportDto);
    }

}
