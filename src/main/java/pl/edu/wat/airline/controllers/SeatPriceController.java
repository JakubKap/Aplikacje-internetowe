package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entities.SeatPrice;
import pl.edu.wat.airline.services.SeatPriceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/seat_price")
public class SeatPriceController {

    private SeatPriceService seats;

    @Autowired
    public SeatPriceController(SeatPriceService seats) {
        this.seats = seats;
    }

    @GetMapping("all_seat_price")
    public Iterable<SeatPrice> getAllSeats(){
        return seats.findAll();
    }

    @GetMapping
    public Optional<SeatPrice> getById(@RequestParam Long id) {
        return seats.findById(id);
    }

    @PostMapping
    public SeatPrice addUser(@RequestBody SeatPrice seatPrice) {
        return seats.save(seatPrice);
    }

    @PutMapping
    public SeatPrice updateUser(@RequestParam Long id, @RequestBody SeatPrice seatRequest) {
        return seats.findById(id).map(seat -> {

            seat.setBusinessClassAdultPrice(seatRequest.getBusinessClassAdultPrice());
            seat.setBusinessClassChildPrice(seatRequest.getBusinessClassChildPrice());
            seat.setBusinessClassInfantPrice(seatRequest.getBusinessClassInfantPrice());

            seat.setEconomicClassAdultPrice(seatRequest.getEconomicClassAdultPrice());
            seat.setEconomicClassChildPrice(seatRequest.getEconomicClassChildPrice());
            seat.setEconomicClassInfantPrice(seatRequest.getEconomicClassInfantPrice());

            seat.setFirstClassAdultPrice(seatRequest.getFirstClassAdultPrice());
            seat.setFirstClassChildPrice(seatRequest.getFirstClassChildPrice());
            seat.setFirstClassInfantPrice(seatRequest.getFirstClassInfantPrice());

            return seats.save(seat);
        }).orElseThrow(() -> new RuntimeException("SeatPriceId " + id + " not found."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return seats.findById(id).map(seat -> {
            seats.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("SeatPriceId " + id + " not found."));
    }
}
