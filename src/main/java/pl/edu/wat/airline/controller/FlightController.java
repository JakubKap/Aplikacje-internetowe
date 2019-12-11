package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.Flight;
import pl.edu.wat.airline.service.FlightService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private FlightService flights;

    @Autowired
    public FlightController(FlightService flights) {
        this.flights = flights;
    }

    @GetMapping("/all_flights")
    public Iterable<Flight> getAllFlights() {
        return flights.findAll();
    }

    @GetMapping("/flight")
    public Optional<Flight> getById(@RequestParam Long id){
        return flights.findById(id);
    }

    @GetMapping("flightStatus")
    public Flight findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(@RequestParam String flightNumber,  @RequestParam String departureDatetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return flights.findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(flightNumber, LocalDateTime.parse(departureDatetime, formatter));
    }

    @PostMapping
    public Flight addFLight(@RequestBody Flight flight){
        return flights.save(flight);
    }

    @PutMapping
    public Flight updateFlight(@RequestParam Long id, @RequestBody Flight flightRequest){
        return flights.findById(id).map(flight -> {
            flight.setFlightNumber(flightRequest.getFlightNumber());
            flight.setDepartureDateTime(flightRequest.getDepartureDateTime());
            flight.setArrivalDateTime(flightRequest.getArrivalDateTime());
            flight.setBoardingDateTime(flightRequest.getBoardingDateTime());
            flight.setGateNumber(flightRequest.getGateNumber());
            flight.setStatus(flightRequest.getStatus());
            flight.setDepartureAirport(flightRequest.getDepartureAirport());
            flight.setArrivalAirport(flightRequest.getArrivalAirport());
            flight.setAirplane(flightRequest.getAirplane());
            flight.setSeatPrice(flightRequest.getSeatPrice());
            return flights.save(flight);
        }).orElseThrow(() -> new RuntimeException("FlightId " + id + " not found."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFlight(@RequestParam Long id){
        return flights.findById(id).map(flight -> {
            flights.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException(("FlightId " + id + "not found.")));
    }

}
