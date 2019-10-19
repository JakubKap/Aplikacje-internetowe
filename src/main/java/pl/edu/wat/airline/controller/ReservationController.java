package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.airline.entity.Reservation;
import pl.edu.wat.airline.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationService reservations;

    @Autowired
    public ReservationController(ReservationService reservations) {
        this.reservations = reservations;
    }

    @GetMapping("/all_reservations")
    public Iterable<Reservation> getAllReservations() {
        return reservations.findAll();
    }
}
