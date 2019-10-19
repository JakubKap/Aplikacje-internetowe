package pl.edu.wat.airline.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "ai_airport")

public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String iata;

    public Airport() {
    }

    public Airport(String name, String iata) {
        this.name = name;
        this.iata = iata;
    }
}
