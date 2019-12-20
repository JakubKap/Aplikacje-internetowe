package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.airline.entities.AvailableSits;
import pl.edu.wat.airline.services.AvailableSitsService;

import java.util.Optional;

@RestController
@RequestMapping("/api/available_sits")
public class AvailableSitsController {

    private AvailableSitsService availableSits;

    @Autowired
    public AvailableSitsController(AvailableSitsService availableSits) {
        this.availableSits = availableSits;
    }

    @GetMapping("/all_available_sits")
    public Iterable<AvailableSits> getAllAvailableSits() {
        return availableSits.findAll();
    }

    @GetMapping
    public Optional<AvailableSits> getById(@RequestParam Long id){
        return availableSits.findById(id);
    }

    @GetMapping("available")
    public Iterable<AvailableSits> findAvailableSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer ekoSeats,
            @RequestParam Integer busSeats,
            @RequestParam Integer pierSeats
    ) {
        return availableSits.findAvailableSits(
                departureDateTime,
                departureAirport,
                arrivalAirport,
                ekoSeats,
                busSeats,
                pierSeats);
    }

    @GetMapping("bus_sits")
    public Iterable<AvailableSits> findAvailableBusSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        return availableSits.findAvailableBusSits(
                departureDateTime,
                departureAirport,
                arrivalAirport,
                classSeatsNum);
    }

    @GetMapping("eko_sits")
    public Iterable<AvailableSits> findAvailableEkoSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        return availableSits.findAvailableEkoSits(
                departureDateTime,
                departureAirport,
                arrivalAirport,
                classSeatsNum);
    }

    @GetMapping("pier_sits")
    public Iterable<AvailableSits> findAvailablePierSits(
            @RequestParam String departureDateTime,
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam Integer classSeatsNum
    ) {
        return availableSits.findAvailablePierSits(
                departureDateTime,
                departureAirport,
                arrivalAirport,
                classSeatsNum);
    }
    //    @GetMapping("available")
//    public AvailableSits findByDepartureDateTimeAndArrivalDateTimeAndDepartureAirportAndArrivalAirportAndEkoAvailableIsGreaterThanEqualAndBusAvailableIsGreaterThanEqualAndPierAvailableIsGreaterThanEqual(
//            @RequestParam String departureDateTime,
//            @RequestParam String arrivalDateTime,
//            @RequestParam String departureAirport,
//            @RequestParam String arrivalAirport,
//            @RequestParam Integer eko,
//            @RequestParam Integer bus,
//            @RequestParam Integer pier
//    ) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return availableSits.findByDepartureDateTimeAndArrivalDateTimeAndDepartureAirportAndArrivalAirportAndEkoAvailableIsGreaterThanEqualAndBusAvailableIsGreaterThanEqualAndPierAvailableIsGreaterThanEqual(
//                LocalDateTime.parse(departureDateTime, formatter),
//                LocalDateTime.parse(arrivalDateTime, formatter),
//                departureAirport,
//                arrivalAirport,
//                eko, bus, pier
//        );
//    }

}
