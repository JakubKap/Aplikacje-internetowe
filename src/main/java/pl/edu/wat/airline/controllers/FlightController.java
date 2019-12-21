package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.dtos.FlightDto;
import pl.edu.wat.airline.services.FlightsServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private FlightsServiceImpl flightsServiceImpl;

    @Autowired
    public FlightController(FlightsServiceImpl flightsServiceImpl) {
        this.flightsServiceImpl = flightsServiceImpl;
    }

    @GetMapping("/all_flights")
    public Iterable<FlightDto> getAllFlights() {
        return flightsServiceImpl.findAll();
    }

    @GetMapping("/flight")
    public FlightDto getByFlightNumber(@RequestParam String flightNumber){
        return flightsServiceImpl.findByFlightNumber(flightNumber);
    }

    @GetMapping("flightStatus")
    public FlightDto findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(@RequestParam String flightNumber, @RequestParam String departureDatetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return flightsServiceImpl.findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(flightNumber, LocalDateTime.parse(departureDatetime, formatter));
    }

    @PostMapping
    public FlightDto addFLight(@RequestBody FlightDto flightDto){
        return flightsServiceImpl.addFlight(flightDto);
    }

    @PutMapping
    public FlightDto updateFlight(@RequestBody FlightDto flightDto){
        return flightsServiceImpl.updateFlight(flightDto);
    }

}
