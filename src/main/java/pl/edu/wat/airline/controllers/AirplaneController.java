package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.dtos.AirplaneDto;
import pl.edu.wat.airline.services.AirplanesServiceImpl;


@RestController
@RequestMapping("/api/airplanes")

public class AirplaneController {

    private AirplanesServiceImpl airplaneServiceImpl;

    @Autowired
    public AirplaneController(AirplanesServiceImpl airplaneServiceImpl) {
        this.airplaneServiceImpl = airplaneServiceImpl;
    }

    @CrossOrigin
    @GetMapping("/all_airplanes")
    public ResponseEntity<Iterable<AirplaneDto>> getAllAirplanes(){
        return new ResponseEntity<>(airplaneServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getByName(@RequestParam String name) {
         AirplaneDto airplaneDto = airplaneServiceImpl.findByName(name);

         if(airplaneDto == null){
             return new ResponseEntity(HttpStatus.NOT_FOUND);
         }

        return new ResponseEntity<>(airplaneDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addAirplane(@RequestBody AirplaneDto airplaneDto) {
        AirplaneDto savedAirplaneDto = airplaneServiceImpl.addAirplane(airplaneDto);

        if(savedAirplaneDto == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(savedAirplaneDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AirplaneDto> updateAirplane(@RequestBody AirplaneDto airplaneDto) {
        return new ResponseEntity<>(airplaneServiceImpl.updateAirplane(airplaneDto), HttpStatus.OK);
    }
}
