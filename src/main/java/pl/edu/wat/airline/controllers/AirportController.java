package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Iterable<AirportDto>> getAllAirports(){
        return new ResponseEntity<>(airportService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getByIata(@RequestParam String iata) {
        AirportDto airportDto = airportService.findByIata(iata);

        if(airportDto == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(airportDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addAirport(@RequestBody AirportDto airportDto) {
        AirportDto savedAirportDto = airportService.addAirport(airportDto);

        if(savedAirportDto == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(savedAirportDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AirportDto> updateAirportName(@RequestBody AirportDto airportDto) {
        return new ResponseEntity<>(airportService.updateAirportName(airportDto), HttpStatus.OK);
    }

}
