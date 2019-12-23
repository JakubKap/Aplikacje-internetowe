package pl.edu.wat.airline.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSitsDto {

    private FlightDto flightDto;
    private AirportDto departureAirportDto;
    private AirportDto arrivalAirportDto;
    private AirplaneDto airplaneDto;
    private SeatPriceDto seatPriceDto;

    private String flightNumber;
    private String gateNumber;
    private String departureDateTime;
    private String arrivalDateTime;
    private String boardingDateTime;
    private String departureAirport;
    private String arrivalAirport;
    private Integer ekoAvailable;
    private Integer busAvailable;
    private Integer pierAvailable;
    private Double businessClassAdultPrice;
    private Double businessClassChildPrice;
    private Double businessClassInfantPrice;
    private Double economicClassAdultPrice;
    private Double economicClassChildPrice;
    private Double economicClassInfantPrice;
    private Double firstClassAdultPrice;
    private Double firstClassChildPrice;
    private Double firstClassInfantPrice;
    private Double price;
}
