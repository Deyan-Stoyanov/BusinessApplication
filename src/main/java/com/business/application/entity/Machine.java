package com.business.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "machines")
public class Machine extends BaseEntity {

    private String machineName;
    private BigDecimal closingForceApplied;
    private BigDecimal columnDistance;

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
}
