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
    @JoinColumn(name = "airplane_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "seatPrice", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SeatPrice seatPrice;

}
