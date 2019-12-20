package pl.edu.wat.airline.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ai_airplane")

public class AirplaneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer economicSeatsAmount;
    private Integer businessSeatsAmount;
    private Integer firstClassSeatsAmount;

    public AirplaneEntity() {
    }

    public AirplaneEntity(String name, Integer economicSeatsAmount, Integer businessSeatsAmount, Integer firstClassSeatsAmount) {
        this.name = name;
        this.economicSeatsAmount = economicSeatsAmount;
        this.businessSeatsAmount = businessSeatsAmount;
        this.firstClassSeatsAmount = firstClassSeatsAmount;
    }
}
