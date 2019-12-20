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

public class Flight {

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
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrivalAirportIata", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "airplaneId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AirplaneEntity airplaneEntity;

    @ManyToOne
    @JoinColumn(name = "seatPrice", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SeatPrice seatPrice;

    public Flight() {
    }

    public Flight(String flightNumber, LocalDateTime departureDate, LocalDateTime arrivalDate, LocalDateTime boardingDate, String gateNumber, String status, Airport departureAirport, Airport arrivalAirport, AirplaneEntity airplaneEntity, SeatPrice seatPrice) {
        this.flightNumber = flightNumber;
        this.departureDateTime = departureDate;
        this.arrivalDateTime = arrivalDate;
        this.boardingDateTime = boardingDate;
        this.gateNumber = gateNumber;
        this.status = status;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airplaneEntity = airplaneEntity;
        this.seatPrice = seatPrice;
    }
}
