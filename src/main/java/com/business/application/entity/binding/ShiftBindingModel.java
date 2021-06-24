package com.business.application.entity.binding;

import com.business.application.enumerations.ShiftType;

public class ShiftBindingModel {
    private ShiftType shiftType;
    private Integer hitCount;
    private Integer wasteCount;
    private Integer barCount;
    private String dateOfShift;
    private String employee;
    private String machine;
    private String element;
    private String alloy;

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public Integer getWasteCount() {
        return wasteCount;
    }

    public void setWasteCount(Integer wasteCount) {
        this.wasteCount = wasteCount;
    }

    public Integer getBarCount() {
        return barCount;
    }

    public void setBarCount(Integer barCount) {
        this.barCount = barCount;
    }

    public String getDateOfShift() {
        return dateOfShift;
    }

    public void setDateOfShift(String dateOfShift) {
        this.dateOfShift = dateOfShift;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getAlloy() {
        return alloy;
    }

    public void setAlloy(String alloy) {
        this.alloy = alloy;
    }
}
