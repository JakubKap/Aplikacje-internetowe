package pl.edu.wat.airline.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "ai_flight")

public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime boardingDateTime;

    private String gateNumber;
    private String status;

    @ManyToOne
    @JoinColumn(name = "departureAirportIata", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AirportEntity departureAirportEntity;

    @ManyToOne
    @JoinColumn(name = "arrivalAirportIata", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AirportEntity arrivalAirportEntity;

    @ManyToOne
    @JoinColumn(name = "airplaneId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AirplaneEntity airplaneEntity;

    @ManyToOne
    @JoinColumn(name = "seatPrice", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SeatPrice seatPrice;

    public FlightEntity() {
    }

    public FlightEntity(String flightNumber, LocalDateTime departureDate, LocalDateTime arrivalDate, LocalDateTime boardingDate, String gateNumber, String status, AirportEntity departureAirportEntity, AirportEntity arrivalAirportEntity, AirplaneEntity airplaneEntity, SeatPrice seatPrice) {
        this.flightNumber = flightNumber;
        this.departureDateTime = departureDate;
        this.arrivalDateTime = arrivalDate;
        this.boardingDateTime = boardingDate;
        this.gateNumber = gateNumber;
        this.status = status;
        this.departureAirportEntity = departureAirportEntity;
        this.arrivalAirportEntity = arrivalAirportEntity;
        this.airplaneEntity = airplaneEntity;
        this.seatPrice = seatPrice;
    }
}
