package pl.edu.wat.airline.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AirplaneDto {
    private String name;
    private Integer economicSeatsAmount;
    private Integer businessSeatsAmount;
    private Integer firstClassSeatsAmount;
}
