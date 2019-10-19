package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.Airport;
import pl.edu.wat.airline.service.AirportService;

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
    public Airport updateAirport(@RequestBody Airport airport) {
        return airports.save(airport);
    }

    @DeleteMapping
    public void deleteAirport(@RequestParam Long id) {
        airports.deleteById(id);
    }

}
