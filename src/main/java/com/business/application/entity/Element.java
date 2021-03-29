package com.business.application.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "elements")
public class Element extends BaseEntity {

    private String elementNumber;
    private Integer quantityProducedOneCycle;
    private Integer weightPerCast;
    private Integer weightPerSingleUnit;
    private BigDecimal pistonSize;
    private Alloy alloy;
    private Machine machine;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "elementNumber", nullable = false)
    public String getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(String elementNumber) {
        this.elementNumber = elementNumber;
    }

    @NotBlank
    @Column(name = "quantityProducedOneCycle", nullable = false)
    public Integer getQuantityProducedOneCycle() {
        return quantityProducedOneCycle;
    }

    public void setQuantityProducedOneCycle(Integer quantityProducedOneCycle) {
        this.quantityProducedOneCycle = quantityProducedOneCycle;
    }

    @NotBlank
    @Column(name = "weightPerCast", nullable = false)
    public Integer getWeightPerCast() {
        return weightPerCast;
    }

    public void setWeightPerCast(Integer weightPerCast) {
        this.weightPerCast = weightPerCast;
    }

    @NotBlank
    @Column(name = "weightPerSingleUnit", nullable = false)
    public Integer getWeightPerSingleUnit() {
        return weightPerSingleUnit;
    }

    public void setWeightPerSingleUnit(Integer weightPerSingleUnit) {
        this.weightPerSingleUnit = weightPerSingleUnit;
    }

    @NotBlank
    @Column(name = "pistonSize", nullable = false)
    public BigDecimal getPistonSize() {
        return pistonSize;
    }

    public void setPistonSize(BigDecimal pistonSize) {
        this.pistonSize = pistonSize;
    }

    @NotBlank
    @ManyToOne(targetEntity = Alloy.class, fetch = FetchType.EAGER)
    public Alloy getAlloy() {
        return alloy;
    }

    public void setAlloy(Alloy alloy) {
        this.alloy = alloy;
    }

    @NotBlank
    @ManyToOne(targetEntity = Machine.class, fetch = FetchType.EAGER)
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
