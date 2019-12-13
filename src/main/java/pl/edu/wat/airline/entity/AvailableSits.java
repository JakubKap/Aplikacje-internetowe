package pl.edu.wat.airline.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "available_seats")
public class AvailableSits {

    @Id
    private Long flightId;

    private Integer airplaneId;
    private Integer seatClassId;
    private Integer departureId;
    private Integer arrivalId;
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

