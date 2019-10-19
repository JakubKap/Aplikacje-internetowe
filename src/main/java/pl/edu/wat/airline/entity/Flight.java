package pl.edu.wat.airline.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalDate boardingDate;
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
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "seatPrice", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SeatPrice seatPrice;

    public Flight() {
    }

    public Flight(String flightNumber, LocalDate departureDate, LocalDate arrivalDate, LocalDate boardingDate, String gateNumber, String status, Airport departureAirport, Airport arrivalAirport, Airplane airplane, SeatPrice seatPrice) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.boardingDate = boardingDate;
        this.gateNumber = gateNumber;
        this.status = status;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airplane = airplane;
        this.seatPrice = seatPrice;
    }
}
