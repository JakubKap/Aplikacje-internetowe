package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.dtos.SeatPriceDto;
import pl.edu.wat.airline.services.SeatPricesServiceImpl;

@RestController
@RequestMapping("/api/seat_price")
public class SeatPriceController {

    private SeatPricesServiceImpl seatPricesServiceImpl;

    @Autowired
    public SeatPriceController(SeatPricesServiceImpl seatPricesServiceImpl) {
        this.seatPricesServiceImpl = seatPricesServiceImpl;
    }

    @GetMapping("all_seat_price")
    public ResponseEntity<Iterable<SeatPriceDto>> getAllSeats(){
        return new ResponseEntity<>(seatPricesServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SeatPriceDto> addSeatPrice(@RequestBody SeatPriceDto seatPriceDto) {
        return new ResponseEntity<>(seatPricesServiceImpl.addSeatPrice(seatPriceDto), HttpStatus.OK);
    }
}
