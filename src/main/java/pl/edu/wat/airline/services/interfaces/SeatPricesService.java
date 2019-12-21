package pl.edu.wat.airline.services.interfaces;

import pl.edu.wat.airline.dtos.SeatPriceDto;

public interface SeatPricesService {
    Iterable<SeatPriceDto> findAll();

    SeatPriceDto addSeatPrice(SeatPriceDto seatPriceDto);
}
