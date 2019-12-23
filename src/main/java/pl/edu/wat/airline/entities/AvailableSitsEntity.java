package pl.edu.wat.airline.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}

