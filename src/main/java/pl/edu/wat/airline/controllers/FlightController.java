package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Iterable<FlightDto>> getAllFlights() {
        return new ResponseEntity<>(flightsServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/flight")
    public ResponseEntity getByFlightNumber(@RequestParam String flightNumber){
        FlightDto flightDto = flightsServiceImpl.findByFlightNumber(flightNumber);
        if(flightDto == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @GetMapping("flightStatus")
    public ResponseEntity findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(@RequestParam String flightNumber, @RequestParam String departureDatetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        FlightDto flightDto = flightsServiceImpl.findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(flightNumber, LocalDateTime.parse(departureDatetime, formatter));
        if(flightDto == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(flightDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addFLight(@RequestBody FlightDto flightDto){
         FlightDto savedFlightDto = flightsServiceImpl.addFlight(flightDto);

         if(savedFlightDto == null){
             return new ResponseEntity(HttpStatus.NO_CONTENT);
         }

         return new ResponseEntity<>(savedFlightDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flightDto){
        return new ResponseEntity<>(flightsServiceImpl.updateFlight(flightDto), HttpStatus.OK);
    }

}
