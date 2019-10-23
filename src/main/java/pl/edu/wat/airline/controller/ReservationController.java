package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.Reservation;
import pl.edu.wat.airline.entity.User;
import pl.edu.wat.airline.service.ReservationService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public Optional<Reservation> getById(@RequestParam Long id){
        return reservations.findById(id);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservations.save(reservation);
    }

    @PutMapping
    public Reservation updateReservation(@RequestParam Long id, @RequestBody Reservation reservationRequest){
        return reservations.findById(id).map(reservation -> {
            reservation.setReservationNo(reservationRequest.getReservationNo());
            reservation.setIsReservationPaid(reservationRequest.getIsReservationPaid());
            reservation.setIsOnlineCheckInMade(reservationRequest.getIsOnlineCheckInMade());
            reservation.setNumOfAdults(reservationRequest.getNumOfAdults());
            reservation.setNumOfInfants(reservationRequest.getNumOfInfants());
            reservation.setNumOfChildren(reservationRequest.getNumOfChildren());
            reservation.setTravelClass(reservationRequest.getTravelClass());
            reservation.setReservationPrice(reservationRequest.getReservationPrice());
            reservation.setFlight(reservationRequest.getFlight());
            reservation.setUser(reservationRequest.getUser());
            return reservations.save(reservation);
        }).orElseThrow(() -> new RuntimeException("ReservationId " + id + " not found."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReservation(@RequestParam Long id){
        return reservations.findById(id).map(reservation -> {
            reservations.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("ReservationId " + id + " not found."));
    }
}
