package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.airline.dtos.AvailableSitsDto;
import pl.edu.wat.airline.services.AvailableSitsServiceImpl;


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
        Iterable<AvailableSitsDto> availableSitsDtos = availableSitsServiceImpl
                .findAvailableBusSits(
                        departureDateTime,
                        departureAirport,
                        arrivalAirport,
                        classSeatsNum);

        if(availableSitsDtos == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(availableSitsDtos, HttpStatus.OK);
    }

    @GetMapping("eko_sits")
    public ResponseEntity<Iterable<AvailableSitsDto>> findAvailableEkoSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        Iterable<AvailableSitsDto> availableSitsDtos = availableSitsServiceImpl
                .findAvailableEkoSits(
                        departureDateTime,
                        departureAirport,
                        arrivalAirport,
                        classSeatsNum);

        if(availableSitsDtos == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(availableSitsDtos, HttpStatus.OK);
    }

    @GetMapping("pier_sits")
    public ResponseEntity<Iterable<AvailableSitsDto>> findAvailablePierSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        Iterable<AvailableSitsDto> availableSitsDtos = availableSitsServiceImpl
                .findAvailablePierSits(
                        departureDateTime,
                        departureAirport,
                        arrivalAirport,
                        classSeatsNum);

        if(availableSitsDtos == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(availableSitsDtos, HttpStatus.OK);
    }
}
