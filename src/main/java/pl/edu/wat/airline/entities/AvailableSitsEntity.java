package pl.edu.wat.airline.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ai_available_sits")
public class AvailableSitsEntity {

    @Id
    private Integer flightId;

    private String airplaneName;
    private Integer seatClassCode;
    private String departureCode;
    private String arrivalCode;

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

