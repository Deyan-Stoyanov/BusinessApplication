package com.business.application.entity.binding;

import java.math.BigDecimal;

public class MachineBindingModel {
    private String machineName;
    private BigDecimal closingForceApplied;
    private BigDecimal columnDistance;

    public MachineBindingModel() {
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public BigDecimal getClosingForceApplied() {
        return closingForceApplied;
    }

    public void setClosingForceApplied(BigDecimal closingForceApplied) {
        this.closingForceApplied = closingForceApplied;
    }

    public BigDecimal getColumnDistance() {
        return columnDistance;
    }

    public void setColumnDistance(BigDecimal columnDistance) {
        this.columnDistance = columnDistance;
    }
}
