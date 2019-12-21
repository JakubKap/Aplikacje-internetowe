package pl.edu.wat.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<SeatPriceDto> getAllSeats(){
        return seatPricesServiceImpl.findAll();
    }

    @PostMapping
    public SeatPriceDto addSeatPrice(@RequestBody SeatPriceDto seatPriceDto) {
        return seatPricesServiceImpl.addSeatPrice(seatPriceDto);
    }
}
