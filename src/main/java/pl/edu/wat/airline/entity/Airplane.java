package pl.edu.wat.airline.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ai_airplane")

public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer economicSeatsAmount;
    private Integer businessSeatsAmount;
    private Integer firstClassSeatsAmount;

    public Airplane() {
    }

    public Airplane(String name, Integer economicSeatsAmount, Integer businessSeatsAmount, Integer firstClassSeatsAmount) {
        this.name = name;
        this.economicSeatsAmount = economicSeatsAmount;
        this.businessSeatsAmount = businessSeatsAmount;
        this.firstClassSeatsAmount = firstClassSeatsAmount;
    }
}
