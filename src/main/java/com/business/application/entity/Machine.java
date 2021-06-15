package com.business.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "machines")
public class Machine extends BaseEntity {

    private String machineName;
    private BigDecimal closingForceApplied;
    private BigDecimal columnDistance;
    private List<Element> elements;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "machineName", nullable = false)
    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    @Column(name = "closingForceApplied")
    public BigDecimal getClosingForceApplied() {
        return closingForceApplied;
    }

    public void setClosingForceApplied(BigDecimal closingForceApplied) {
        this.closingForceApplied = closingForceApplied;
    }

    @Column(name = "columnDistance")
    public BigDecimal getColumnDistance() {
        return columnDistance;
    }

    public void setColumnDistance(BigDecimal columnDistance) {
        this.columnDistance = columnDistance;
    }

    @OneToMany(mappedBy = "machine", targetEntity = Element.class)
    public List<Element> getElements() {
        return this.elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = Collections.unmodifiableList(elements);
    }
}
