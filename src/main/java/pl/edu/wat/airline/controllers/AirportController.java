package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entities.Airport;
import pl.edu.wat.airline.services.AirportService;

import java.util.Optional;

@RestController
@RequestMapping("/api/airports")

public class AirportController {

    private AirportService airports;

    @Autowired
    public AirportController(AirportService airports) {
        this.airports = airports;
    }

    @GetMapping("/all_airports")
    public Iterable<Airport> getAllAirports(){
        return airports.findAll();
    }

    @GetMapping
    public Optional<Airport> getById(@RequestParam Long id) {
        return airports.findById(id);
    }

    @PostMapping
    public Airport addAirport(@RequestBody Airport airport) {
        return airports.save(airport);
    }

    @PutMapping
    public Airport updateAirport(@RequestParam Long id, @RequestBody Airport airportRequest) {
        return airports.findById(id).map(airport -> {
            airport.setName(airportRequest.getName());
            airport.setIata(airportRequest.getIata());
            return airports.save(airport);
        }).orElseThrow(() -> new RuntimeException("AirportId " + id + " not found."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAirport(@RequestParam Long id) {
        return airports.findById(id).map(airport -> {
            airports.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("AirportId " + id + " not found."));
    }

}
