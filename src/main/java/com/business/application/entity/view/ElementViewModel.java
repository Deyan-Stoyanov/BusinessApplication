package com.business.application.entity.view;

import java.math.BigDecimal;

public class ElementViewModel {

    private String elementNumber;
    private Integer quantityProducedOneCycle;
    private Integer weightPerCast;
    private Integer weightPerSingleUnit;
    private BigDecimal pistonSize;

    public ElementViewModel() {
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
}
