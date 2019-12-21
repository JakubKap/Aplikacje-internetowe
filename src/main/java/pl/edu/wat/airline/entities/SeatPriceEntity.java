package pl.edu.wat.airline.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ai_seat_price")

public class SeatPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double economicClassAdultPrice;
    private Double businessClassAdultPrice;
    private Double firstClassAdultPrice;
    private Double economicClassInfantPrice;
    private Double businessClassInfantPrice;
    private Double firstClassInfantPrice;
    private Double economicClassChildPrice;
    private Double businessClassChildPrice;
    private Double firstClassChildPrice;

    public SeatPriceEntity() {
    }

    public SeatPriceEntity(Double economicClassAdultPrice, Double businessClassAdultPrice, Double firstClassAdultPrice, Double economicClassInfantPrice, Double businessClassInfantPrice, Double firstClassInfantPrice, Double economicClassChildPrice, Double businessClassChildPrice, Double firstClassChildPrice) {
        this.economicClassAdultPrice = economicClassAdultPrice;
        this.businessClassAdultPrice = businessClassAdultPrice;
        this.firstClassAdultPrice = firstClassAdultPrice;
        this.economicClassInfantPrice = economicClassInfantPrice;
        this.businessClassInfantPrice = businessClassInfantPrice;
        this.firstClassInfantPrice = firstClassInfantPrice;
        this.economicClassChildPrice = economicClassChildPrice;
        this.businessClassChildPrice = businessClassChildPrice;
        this.firstClassChildPrice = firstClassChildPrice;
    }

    public SeatPriceEntity(Long id, Double economicClassAdultPrice, Double businessClassAdultPrice, Double firstClassAdultPrice, Double economicClassInfantPrice, Double businessClassInfantPrice, Double firstClassInfantPrice, Double economicClassChildPrice, Double businessClassChildPrice, Double firstClassChildPrice) {
        this.id = id;
        this.economicClassAdultPrice = economicClassAdultPrice;
        this.businessClassAdultPrice = businessClassAdultPrice;
        this.firstClassAdultPrice = firstClassAdultPrice;
        this.economicClassInfantPrice = economicClassInfantPrice;
        this.businessClassInfantPrice = businessClassInfantPrice;
        this.firstClassInfantPrice = firstClassInfantPrice;
        this.economicClassChildPrice = economicClassChildPrice;
        this.businessClassChildPrice = businessClassChildPrice;
        this.firstClassChildPrice = firstClassChildPrice;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEconomicClassAdultPrice() {
        return economicClassAdultPrice;
    }

    public void setEconomicClassAdultPrice(Double economicClassAdultPrice) {
        this.economicClassAdultPrice = economicClassAdultPrice;
    }

    public Double getBusinessClassAdultPrice() {
        return businessClassAdultPrice;
    }

    public void setBusinessClassAdultPrice(Double businessClassAdultPrice) {
        this.businessClassAdultPrice = businessClassAdultPrice;
    }

    public Double getFirstClassAdultPrice() {
        return firstClassAdultPrice;
    }

    public void setFirstClassAdultPrice(Double firstClassAdultPrice) {
        this.firstClassAdultPrice = firstClassAdultPrice;
    }

    public Double getEconomicClassInfantPrice() {
        return economicClassInfantPrice;
    }

    public void setEconomicClassInfantPrice(Double economicClassInfantPrice) {
        this.economicClassInfantPrice = economicClassInfantPrice;
    }

    public Double getBusinessClassInfantPrice() {
        return businessClassInfantPrice;
    }

    public void setBusinessClassInfantPrice(Double businessClassInfantPrice) {
        this.businessClassInfantPrice = businessClassInfantPrice;
    }

    public Double getFirstClassInfantPrice() {
        return firstClassInfantPrice;
    }

    public void setFirstClassInfantPrice(Double firstClassInfantPrice) {
        this.firstClassInfantPrice = firstClassInfantPrice;
    }

    public Double getEconomicClassChildPrice() {
        return economicClassChildPrice;
    }

    public void setEconomicClassChildPrice(Double economicClassChildPrice) {
        this.economicClassChildPrice = economicClassChildPrice;
    }

    public Double getBusinessClassChildPrice() {
        return businessClassChildPrice;
    }

    public void setBusinessClassChildPrice(Double businessClassChildPrice) {
        this.businessClassChildPrice = businessClassChildPrice;
    }

    public Double getFirstClassChildPrice() {
        return firstClassChildPrice;
    }

    public void setFirstClassChildPrice(Double firstClassChildPrice) {
        this.firstClassChildPrice = firstClassChildPrice;
    }
}
