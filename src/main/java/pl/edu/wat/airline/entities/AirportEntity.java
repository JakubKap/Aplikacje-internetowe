package pl.edu.wat.airline.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ai_airport")

public class AirportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iata;
    private String name;

    public AirportEntity() {
    }

    public AirportEntity(String iata, String name) {
        this.iata = iata;
        this.name = name;
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

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }
}
