package pl.edu.wat.airline.services.interfaces;

import pl.edu.wat.airline.dtos.ReservationDto;

public interface ReservationService {

    Iterable<ReservationDto> findAll();

    Iterable<ReservationDto> findByUserLogin(String userLogin);

    ReservationDto findByReservationNo(String reservationNo);
}
