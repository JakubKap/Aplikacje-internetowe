package pl.edu.wat.airline.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ai_reservation")

public class Reservation implements Serializable {

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
    private FlightEntity flightEntity;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;


    public Reservation(Long id, String reservationNo, Boolean isReservationPaid, Boolean isOnlineCheckInMade, Integer numOfAdults, Integer numOfInfants, Integer numOfChildren, String travelClass, Double reservationPrice, FlightEntity flightEntity, UserEntity userEntity) {
        this.id = id;
        this.reservationNo = reservationNo;
        this.isReservationPaid = isReservationPaid;
        this.isOnlineCheckInMade = isOnlineCheckInMade;
        this.numOfAdults = numOfAdults;
        this.numOfInfants = numOfInfants;
        this.numOfChildren = numOfChildren;
        this.travelClass = travelClass;
        this.reservationPrice = reservationPrice;
        this.flightEntity = flightEntity;
        this.userEntity = userEntity;
    }
}
