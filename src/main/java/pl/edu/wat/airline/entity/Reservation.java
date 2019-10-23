package pl.edu.wat.airline.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ai_reservation")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reservationNo;
    private Boolean isReservationPaid;
    private Boolean isOnlineCheckInMade;
    private Integer numOfAdults;
    private Integer numOfInfants;
    private Integer numOfChildren;
    private String travelClass;
    private Double reservationPrice;

    @ManyToOne
    @JoinColumn(name = "flightId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
