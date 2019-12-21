package pl.edu.wat.airline.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SeatPriceDto {
    private Double economicClassAdultPrice;
    private Double businessClassAdultPrice;
    private Double firstClassAdultPrice;
    private Double economicClassInfantPrice;
    private Double businessClassInfantPrice;
    private Double firstClassInfantPrice;
    private Double economicClassChildPrice;
    private Double businessClassChildPrice;
    private Double firstClassChildPrice;
}
