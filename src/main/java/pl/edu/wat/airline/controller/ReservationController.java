package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.Reservation;
import pl.edu.wat.airline.service.EmailService;
import pl.edu.wat.airline.service.ReservationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationService reservations;
    private EmailService email;

    @Autowired
    public ReservationController(ReservationService reservations, EmailService email) {
        this.reservations = reservations;
        this.email = email;
    }

    @GetMapping("/all_reservations")
    public Iterable<Reservation> getAllReservations() {
        return reservations.findAll();
    }

    @GetMapping
    public Optional<Reservation> getById(@RequestParam Long id){
        return reservations.findById(id);
    }

    @GetMapping("/user_reservation")
    public List<Reservation> findByUserId(@RequestParam Long userId){
        return reservations.findByUserId(userId);
    }

    @GetMapping("/reservation")
    public Optional<Reservation> getByReservationNo(@RequestParam String reservationNo){
        return reservations.findByReservationNo(reservationNo);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        try {
            email.sendEmail(reservation.getUser().getEmail(),"AirportApp new reservation: " + reservation.getReservationNo(), "You successfully added new reservation with number " + reservation.getReservationNo()
            +". Reservation is waiting for payment - check your basket."
            );
        } catch (MailAuthenticationException e) {
            System.out.println("Wrong user email address");
        }
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

    @PutMapping("/paid")
    public Reservation updatePaidStatus(@RequestParam Long id) {
        return reservations.findById(id).map(reservation -> {
            reservation.setIsReservationPaid(true);
            return reservations.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Cannot pay for reservationId " + id + "."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReservation(@RequestParam Long id){
        return reservations.findById(id).map(reservation -> {
            reservations.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("ReservationId " + id + " not found."));
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserReservation(@RequestBody Reservation reservation) {
        try {
            email.sendEmail(reservation.getUser().getEmail(),"AirportApp reservation No. " + reservation.getReservationNo(), "Faithfully AirportApp team");
        } catch (MailAuthenticationException e) {
            System.out.println("Wrong user email address");
        }
        return reservations.findById(reservation.getId()).map(r -> {
            reservations.deleteById(r.getId());
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("ReservationId " + reservation.getId() + " not found."));
    }
}
