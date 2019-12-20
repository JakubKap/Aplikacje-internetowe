package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.dtos.AirplaneDto;
import pl.edu.wat.airline.services.AirplaneServiceImpl;


@RestController
@RequestMapping("/api/airplanes")

public class AirplaneController {

    private AirplaneServiceImpl airplaneServiceImpl;

    @Autowired
    public AirplaneController(AirplaneServiceImpl airplaneServiceImpl) {
        this.airplaneServiceImpl = airplaneServiceImpl;
    }

    @CrossOrigin
    @GetMapping("/all_airplanes")
    public Iterable<AirplaneDto> getAllAirplanes(){
        return airplaneServiceImpl.findAll();
    }

    @GetMapping
    public AirplaneDto getByName(@RequestParam String name) {
        return airplaneServiceImpl.findByName(name);
    }

    @PostMapping
    public AirplaneDto addAirplane(@RequestBody AirplaneDto airplaneDto) {
        return airplaneServiceImpl.addAirplane(airplaneDto);
    }

    @PutMapping
    public AirplaneDto updateAirplane(@RequestBody AirplaneDto airplaneDto) {
        return airplaneServiceImpl.updateAirplane(airplaneDto);
    }
}
