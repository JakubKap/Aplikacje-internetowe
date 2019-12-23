package pl.edu.wat.airline.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private String reservationNo;
    private Boolean isReservationPaid;
    private Boolean isOnlineCheckInMade;
    private Integer numOfAdults;
    private Integer numOfInfants;
    private Integer numOfChildren;
    private String travelClass;
    private Double reservationPrice;

    private FlightDto flightDto;
    private UserDto userDto;

}
