package com.business.application.entity.binding;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class MachineBindingModel {
    private String machineName;
    private BigDecimal closingForceApplied;
    private BigDecimal columnDistance;

    public MachineBindingModel() {
    }

    @NotBlank
    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    @NotBlank
    public BigDecimal getClosingForceApplied() {
        return closingForceApplied;
    }

    public void setClosingForceApplied(BigDecimal closingForceApplied) {
        this.closingForceApplied = closingForceApplied;
    }

    @NotBlank
    public BigDecimal getColumnDistance() {
        return columnDistance;
    }

    public void setColumnDistance(BigDecimal columnDistance) {
        this.columnDistance = columnDistance;
    }
}
