package com.business.application.entity.binding;

import java.math.BigDecimal;

public class ElementBindingModel {

    private String elementNumber;
    private Integer quantityProducedOneCycle;
    private Integer weightPerCast;
    private Integer weightPerSingleUnit;
    private BigDecimal pistonSize;
    private String alloy;
    private String machine;

    public ElementBindingModel() {
    }

    public String getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(String elementNumber) {
        this.elementNumber = elementNumber;
    }

    public Integer getQuantityProducedOneCycle() {
        return quantityProducedOneCycle;
    }

    public void setQuantityProducedOneCycle(Integer quantityProducedOneCycle) {
        this.quantityProducedOneCycle = quantityProducedOneCycle;
    }

    public Integer getWeightPerCast() {
        return weightPerCast;
    }

    public void setWeightPerCast(Integer weightPerCast) {
        this.weightPerCast = weightPerCast;
    }

    public Integer getWeightPerSingleUnit() {
        return weightPerSingleUnit;
    }

    public void setWeightPerSingleUnit(Integer weightPerSingleUnit) {
        this.weightPerSingleUnit = weightPerSingleUnit;
    }

    public BigDecimal getPistonSize() {
        return pistonSize;
    }

    public void setPistonSize(BigDecimal pistonSize) {
        this.pistonSize = pistonSize;
    }

    public String getAlloy() {
        return alloy;
    }

    public void setAlloy(String alloy) {
        this.alloy = alloy;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }
}
