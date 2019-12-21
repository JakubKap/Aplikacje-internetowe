package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entities.Reservation;
import pl.edu.wat.airline.services.EmailService;
import pl.edu.wat.airline.services.ReservationService;

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
    public List<Reservation> findByUserLogin(@RequestParam String userLogin){
        return reservations.findByUserLogin(userLogin);
    }

    @GetMapping("/reservation")
    public Optional<Reservation> getByReservationNo(@RequestParam String reservationNo){
        return reservations.findByReservationNo(reservationNo);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservations.save(reservation);
    }

    @GetMapping("mailAboutReservation")
    public void sendMailAboutReservation(@RequestParam String userEmail, @RequestParam String reservationNo) {
        try {
            email.sendEmail(userEmail,"AirportApp new reservation: " + reservationNo, "You successfully added new reservation with number " + reservationNo
                    +". Reservation is waiting for payment - check your basket."
            );
        } catch (MailAuthenticationException e) {
            System.out.println("Wrong user email address");
        }
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
            reservation.setFlightEntity(reservationRequest.getFlightEntity());
            reservation.setUserEntity(reservationRequest.getUserEntity());
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
            email.sendEmail(reservation.getUserEntity().getEmail(),"AirportApp reservation No. " + reservation.getReservationNo(), "Faithfully AirportApp team");
        } catch (MailAuthenticationException e) {
            System.out.println("Wrong user email address");
        }
        return reservations.findById(reservation.getId()).map(r -> {
            reservations.deleteById(r.getId());
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("ReservationId " + reservation.getId() + " not found."));
    }
}
