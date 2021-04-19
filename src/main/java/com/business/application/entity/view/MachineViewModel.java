package com.business.application.entity.view;

import java.math.BigDecimal;

public class MachineViewModel {

    private String id;
    private String machineName;
    private BigDecimal closingForceApplied;
    private BigDecimal columnDistance;

    public MachineViewModel() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
