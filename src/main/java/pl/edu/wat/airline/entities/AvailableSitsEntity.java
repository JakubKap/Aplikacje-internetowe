package pl.edu.wat.airline.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@Table(name = "available_seats")
public class AvailableSitsEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flightId", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private FlightEntity flightEntity;

    @ManyToOne
    @JoinColumn(name = "airplaneId", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AirplaneEntity airplaneEntity;

    @ManyToOne
    @JoinColumn(name = "seatClassId", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private SeatPriceEntity seatClassEntity;

    @ManyToOne
    @JoinColumn(name = "departureId", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AirportEntity departureEntity;

    @ManyToOne
    @JoinColumn(name = "arrivalId", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AirportEntity arrivalEntity;

//    private Integer flightId;
//    private Integer airplaneId;
//    private Integer seatClassId;
//    private Integer departureId;
//    private Integer arrivalId;

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

    public AvailableSitsEntity(Long id, FlightEntity flightEntity, AirplaneEntity airplaneEntity, SeatPriceEntity seatClassEntity, AirportEntity departureEntity, AirportEntity arrivalEntity, String flightNumber, String gateNumber, String departureDateTime, String arrivalDateTime, String boardingDateTime, String departureAirport, String arrivalAirport, Integer ekoAvailable, Integer busAvailable, Integer pierAvailable, Double businessClassAdultPrice, Double businessClassChildPrice, Double businessClassInfantPrice, Double economicClassAdultPrice, Double economicClassChildPrice, Double economicClassInfantPrice, Double firstClassAdultPrice, Double firstClassChildPrice, Double firstClassInfantPrice, Double price) {
        this.id = id;
        this.flightEntity = flightEntity;
        this.airplaneEntity = airplaneEntity;
        this.seatClassEntity = seatClassEntity;
        this.departureEntity = departureEntity;
        this.arrivalEntity = arrivalEntity;
        this.flightNumber = flightNumber;
        this.gateNumber = gateNumber;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.boardingDateTime = boardingDateTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.ekoAvailable = ekoAvailable;
        this.busAvailable = busAvailable;
        this.pierAvailable = pierAvailable;
        this.businessClassAdultPrice = businessClassAdultPrice;
        this.businessClassChildPrice = businessClassChildPrice;
        this.businessClassInfantPrice = businessClassInfantPrice;
        this.economicClassAdultPrice = economicClassAdultPrice;
        this.economicClassChildPrice = economicClassChildPrice;
        this.economicClassInfantPrice = economicClassInfantPrice;
        this.firstClassAdultPrice = firstClassAdultPrice;
        this.firstClassChildPrice = firstClassChildPrice;
        this.firstClassInfantPrice = firstClassInfantPrice;
        this.price = price;
    }
}

