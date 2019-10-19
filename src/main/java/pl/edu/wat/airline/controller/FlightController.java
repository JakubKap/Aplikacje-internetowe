package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.airline.entity.Flight;
import pl.edu.wat.airline.service.FlightService;

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
}
