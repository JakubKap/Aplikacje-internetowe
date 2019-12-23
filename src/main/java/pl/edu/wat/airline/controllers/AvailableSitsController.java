package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.airline.dtos.AvailableSitsDto;
import pl.edu.wat.airline.entities.AvailableSitsEntity;
import pl.edu.wat.airline.services.AvailableSitsServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/available_sits")
public class AvailableSitsController {

    private AvailableSitsServiceImpl availableSitsServiceImpl;

    @Autowired
    public AvailableSitsController(AvailableSitsServiceImpl availableSitsServiceImpl) {
        this.availableSitsServiceImpl = availableSitsServiceImpl;
    }

    @GetMapping("/all_available_sits")
    public ResponseEntity<Iterable<AvailableSitsDto>> getAllAvailableSits() {
        return new ResponseEntity<>(availableSitsServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("bus_sits")
    public ResponseEntity<Iterable<AvailableSitsDto>> findAvailableBusSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        return new ResponseEntity<>(availableSitsServiceImpl
                .findAvailableBusSits(departureDateTime, departureAirport, arrivalAirport, classSeatsNum), HttpStatus.OK);
    }

    @GetMapping("eko_sits")
    public ResponseEntity<Iterable<AvailableSitsDto>> findAvailableEkoSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        return new ResponseEntity<>(availableSitsServiceImpl
                .findAvailableEkoSits(departureDateTime, departureAirport, arrivalAirport, classSeatsNum), HttpStatus.OK);
    }

    @GetMapping("pier_sits")
    public ResponseEntity<Iterable<AvailableSitsDto>> findAvailablePierSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        return new ResponseEntity<>(availableSitsServiceImpl
                .findAvailablePierSits(departureDateTime, departureAirport, arrivalAirport, classSeatsNum), HttpStatus.OK);
    }
//    @GetMapping
//    public Optional<AvailableSitsEntity> getById(@RequestParam Long id){
//        return availableSits.findById(id);
//    }
//
//    @GetMapping("available")
//    public Iterable<AvailableSitsEntity> findAvailableSits(
//            @RequestParam String departureDateTime,
//            @RequestParam String departureAirport,
//            @RequestParam String arrivalAirport,
//            @RequestParam Integer ekoSeats,
//            @RequestParam Integer busSeats,
//            @RequestParam Integer pierSeats
//    ) {
//        return availableSits.findAvailableSits(
//                departureDateTime,
//                departureAirport,
//                arrivalAirport,
//                ekoSeats,
//                busSeats,
//                pierSeats);
//    }
//

//
//    @GetMapping("eko_sits")
//    public Iterable<AvailableSitsEntity> findAvailableEkoSits(
//            @RequestParam String departureDateTime,
//            @RequestParam String departureAirport,
//            @RequestParam String arrivalAirport,
//            @RequestParam Integer classSeatsNum
//    ) {
//        return availableSits.findAvailableEkoSits(
//                departureDateTime,
//                departureAirport,
//                arrivalAirport,
//                classSeatsNum);
//    }
//
//    @GetMapping("pier_sits")
//    public Iterable<AvailableSitsEntity> findAvailablePierSits(
//            @RequestParam String departureDateTime,
//            @RequestParam String departureAirport,
//            @RequestParam String arrivalAirport,
//            @RequestParam Integer classSeatsNum
//    ) {
//        return availableSits.findAvailablePierSits(
//                departureDateTime,
//                departureAirport,
//                arrivalAirport,
//                classSeatsNum);
//    }
}
