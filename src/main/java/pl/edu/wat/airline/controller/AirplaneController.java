package pl.edu.wat.airline.controller;

import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.Airplane;
import pl.edu.wat.airline.service.AirplaneService;

import java.util.Optional;

@RestController
@RequestMapping("/api/airplanes")

public class AirplaneController {

    private AirplaneService airplanes;

    @Autowired
    public AirplaneController(AirplaneService airplanes) {
        this.airplanes = airplanes;
    }

    @CrossOrigin
    @GetMapping("/all_airplanes")
    public Iterable<Airplane> getAllAirplanes(){
        return airplanes.findAll();
    }

    @GetMapping
    public Optional<Airplane> getById(@RequestParam Long id) {
        return airplanes.findById(id);
    }

    @PostMapping
    public Airplane addAirplane(@RequestBody Airplane airplane) {
        return airplanes.save(airplane);
    }

    @PutMapping
    public Airplane updateAirplane(@RequestParam Long id, @RequestBody Airplane airplaneRequest) {
        return airplanes.findById(id).map(airplane -> {
            airplane.setName(airplaneRequest.getName());
            airplane.setBusinessSeatsAmount(airplaneRequest.getBusinessSeatsAmount());
            airplane.setEconomicSeatsAmount(airplaneRequest.getEconomicSeatsAmount());
            airplane.setFirstClassSeatsAmount(airplaneRequest.getFirstClassSeatsAmount());
            return airplanes.save(airplane);
        }).orElseThrow(() -> new ResourceRequestDeniedException("AirportId " + id + " not found."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAirplane(@RequestParam Long id) {
        return airplanes.findById(id).map(airport -> {
            airplanes.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceRequestDeniedException("AirportId " + id + " not found."));
    }
}