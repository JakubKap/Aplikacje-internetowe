package pl.edu.wat.airline.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEconomicSeatsAmount() {
        return economicSeatsAmount;
    }

    public void setEconomicSeatsAmount(Integer economicSeatsAmount) {
        this.economicSeatsAmount = economicSeatsAmount;
    }

    public Integer getBusinessSeatsAmount() {
        return businessSeatsAmount;
    }

    public void setBusinessSeatsAmount(Integer businessSeatsAmount) {
        this.businessSeatsAmount = businessSeatsAmount;
    }

    public Integer getFirstClassSeatsAmount() {
        return firstClassSeatsAmount;
    }

    public void setFirstClassSeatsAmount(Integer firstClassSeatsAmount) {
        this.firstClassSeatsAmount = firstClassSeatsAmount;
    }
}
