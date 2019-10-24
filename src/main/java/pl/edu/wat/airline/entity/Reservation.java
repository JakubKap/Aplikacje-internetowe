package pl.edu.wat.airline.entity;

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
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public Reservation(Long id, String reservationNo, Boolean isReservationPaid, Boolean isOnlineCheckInMade, Integer numOfAdults, Integer numOfInfants, Integer numOfChildren, String travelClass, Double reservationPrice, Flight flight, User user) {
        this.id = id;
        this.reservationNo = reservationNo;
        this.isReservationPaid = isReservationPaid;
        this.isOnlineCheckInMade = isOnlineCheckInMade;
        this.numOfAdults = numOfAdults;
        this.numOfInfants = numOfInfants;
        this.numOfChildren = numOfChildren;
        this.travelClass = travelClass;
        this.reservationPrice = reservationPrice;
        this.flight = flight;
        this.user = user;
    }
/*
    public Reservation(long id, String reservationNo, boolean isReservationPaid, boolean isOnlineCheckInMade, int numOfAdults, int numOfInfants, int numOfChildren, String travelClass, double reservationPrice, Flight flight, User user) {
        this.id = id;
        this.reservationNo = reservationNo;
        this.isReservationPaid = isReservationPaid;
        this.isOnlineCheckInMade = isOnlineCheckInMade;
        this.numOfAdults = numOfAdults;
        this.numOfInfants = numOfInfants;
        this.numOfChildren = numOfChildren;
        this.travelClass = travelClass;
        this.reservationPrice = reservationPrice;
        this.flight = flight;
        this.user = user;
    }*/
}
