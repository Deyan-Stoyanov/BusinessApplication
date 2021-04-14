package com.business.application.entity.view;

import java.math.BigDecimal;

public class AlloyViewModel {

    private String alloyNumber;
    private String alloyIdentification;
    private Integer weight;
    private Integer units;
    private BigDecimal weightPerUnit;

    public AlloyViewModel() {
    }

    public String getAlloyNumber() {
        return alloyNumber;
    }

    public void setAlloyNumber(String alloyNumber) {
        this.alloyNumber = alloyNumber;
    }

    public String getAlloyIdentification() {
        return alloyIdentification;
    }

    public void setAlloyIdentification(String alloyIdentification) {
        this.alloyIdentification = alloyIdentification;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public BigDecimal getWeightPerUnit() {
        return weightPerUnit;
    }

    public void setWeightPerUnit(BigDecimal weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }
}
