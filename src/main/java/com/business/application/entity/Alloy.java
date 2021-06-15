package com.business.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "alloys")
public class Alloy extends BaseEntity {

    private String alloyNumber;
    private String alloyIdentification;
    private BigDecimal weight;
    private Integer units;
    private BigDecimal weightPerUnit;
    private List<Element> elements;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "alloyNumber", nullable = false)
    public String getAlloyNumber() {
        return alloyNumber;
    }

    public void setAlloyNumber(String alloyNumber) {
        this.alloyNumber = alloyNumber;
    }

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "alloyIdentification", nullable = false)
    public String getAlloyIdentification() {
        return alloyIdentification;
    }

    public void setAlloyIdentification(String alloyIdentification) {
        this.alloyIdentification = alloyIdentification;
    }

    @Column(name = "weight")
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Column(name = "units")
    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    @Column(name = "weightPerUnit")
    public BigDecimal getWeightPerUnit() {
        return weightPerUnit;
    }

    public void setWeightPerUnit(BigDecimal weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }

    @OneToMany(mappedBy = "alloy", targetEntity = Element.class)
    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = Collections.unmodifiableList(elements);
    }
}
