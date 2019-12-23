package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.dtos.ReservationDto;
import pl.edu.wat.airline.entities.ReservationEntity;
import pl.edu.wat.airline.services.EmailService;
import pl.edu.wat.airline.services.ReservationServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationServiceImpl reservationServiceImpl;
    private EmailService email;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationServiceImpl, EmailService email) {
        this.reservationServiceImpl = reservationServiceImpl;
        this.email = email;
    }

    @GetMapping("/all_reservations")
    public ResponseEntity<Iterable<ReservationDto>> getAllReservations() {
        return new ResponseEntity<>(reservationServiceImpl.findAll(), HttpStatus.OK);
    }

//    @GetMapping
//    public Optional<ReservationEntity> getById(@RequestParam Long id){
//        return reservations.findById(id);
//    }

    @GetMapping("/user_reservation")
    public ResponseEntity<ReservationDto> findByUserLogin(@RequestParam String userLogin) {
        ReservationDto reservationDto = reservationServiceImpl.findByUserLogin(userLogin);
        if(reservationDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

    @GetMapping("/reservation")
    public ResponseEntity<ReservationDto> getReservationNo(@RequestParam String reservationNo) {
        ReservationDto reservationDto = reservationServiceImpl.findByReservationNo(reservationNo);
        if(reservationDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

//    @GetMapping("/reservation")
//    public Optional<ReservationEntity> getByReservationNo(@RequestParam String reservationNo){
//        return reservations.findByReservationNo(reservationNo);
//    }

//    @PostMapping
//    public ReservationEntity addReservation(@RequestBody ReservationEntity reservationEntity){
//        return reservations.save(reservationEntity);
//    }
    @PostMapping
    public ResponseEntity addReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto savedReservationDto = reservationServiceImpl.addReservation(reservationDto);

        if(savedReservationDto == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(savedReservationDto, HttpStatus.OK);
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

//    @PutMapping
//    public ReservationEntity updateReservation(@RequestParam Long id, @RequestBody ReservationEntity reservationEntityRequest){
//        return reservations.findById(id).map(reservationEntity -> {
//            reservationEntity.setReservationNo(reservationEntityRequest.getReservationNo());
//            reservationEntity.setIsReservationPaid(reservationEntityRequest.getIsReservationPaid());
//            reservationEntity.setIsOnlineCheckInMade(reservationEntityRequest.getIsOnlineCheckInMade());
//            reservationEntity.setNumOfAdults(reservationEntityRequest.getNumOfAdults());
//            reservationEntity.setNumOfInfants(reservationEntityRequest.getNumOfInfants());
//            reservationEntity.setNumOfChildren(reservationEntityRequest.getNumOfChildren());
//            reservationEntity.setTravelClass(reservationEntityRequest.getTravelClass());
//            reservationEntity.setReservationPrice(reservationEntityRequest.getReservationPrice());
//            reservationEntity.setFlightEntity(reservationEntityRequest.getFlightEntity());
//            reservationEntity.setUserEntity(reservationEntityRequest.getUserEntity());
//            return reservations.save(reservationEntity);
//        }).orElseThrow(() -> new RuntimeException("ReservationId " + id + " not found."));
//    }

    @PutMapping
    public ResponseEntity<ReservationDto> updateReservation(@RequestBody ReservationDto reservationDto) {
        return new ResponseEntity<>(reservationServiceImpl.updateReservation(reservationDto), HttpStatus.OK);
    }

    @PutMapping("/paid")
    public ReservationEntity updatePaidStatus(@RequestParam Long id) {
        return reservations.findById(id).map(reservationEntity -> {
            reservationEntity.setIsReservationPaid(true);
            return reservations.save(reservationEntity);
        }).orElseThrow(() -> new RuntimeException("Cannot pay for reservationId " + id + "."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReservation(@RequestParam Long id){
        return reservations.findById(id).map(reservationEntity -> {
            reservations.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("ReservationId " + id + " not found."));
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserReservation(@RequestBody ReservationEntity reservationEntity) {
        try {
            email.sendEmail(reservationEntity.getUserEntity().getEmail(),"AirportApp reservation No. " + reservationEntity.getReservationNo(), "Faithfully AirportApp team");
        } catch (MailAuthenticationException e) {
            System.out.println("Wrong user email address");
        }
        return reservations.findById(reservationEntity.getId()).map(r -> {
            reservations.deleteById(r.getId());
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("ReservationId " + reservationEntity.getId() + " not found."));
    }
}
