package pl.edu.wat.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.SeatPrice;
import pl.edu.wat.airline.service.SeatPriceService;

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
    public SeatPrice updateUser(@RequestBody SeatPrice seatPrice) {
        return seats.save(seatPrice);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id) {
        seats.deleteById(id);
    }

}
