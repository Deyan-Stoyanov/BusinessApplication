package com.business.application.entity.binding;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class AlloyBindingModel {

    private String alloyNumber;
    private String alloyIdentification;
    private BigDecimal weight;
    private Integer units;

    public AlloyBindingModel() {
    }

    @NotBlank
    public String getAlloyNumber() {
        return alloyNumber;
    }

    public void setAlloyNumber(String alloyNumber) {
        this.alloyNumber = alloyNumber;
    }

    @NotBlank
    public String getAlloyIdentification() {
        return alloyIdentification;
    }

    public void setAlloyIdentification(String alloyIdentification) {
        this.alloyIdentification = alloyIdentification;
    }

    @NotBlank
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @NotBlank
    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

}
