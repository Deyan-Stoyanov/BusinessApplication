package com.business.application.entity.view;

import java.math.BigDecimal;

public class AlloyViewModel {

    private String id;
    private String alloyNumber;
    private String alloyIdentification;
    private BigDecimal weight;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
