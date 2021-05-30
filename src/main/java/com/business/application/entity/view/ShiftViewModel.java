package com.business.application.entity.view;

import com.business.application.enumerations.ShiftType;

import java.util.Date;

public class ShiftViewModel {

    private String id;
    private ShiftType shiftType;
    private Integer hitCount;
    private Integer wasteCount;
    private Integer barCount;
    private Date dateOfShift;
    private EmployeeViewModel employee;
    private MachineViewModel machineViewModel;
    private ElementViewModel element;
    private AlloyViewModel alloy;

    public ShiftViewModel() {
    }

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

    public Date getDateOfShift() {
        return dateOfShift;
    }

    public void setDateOfShift(Date dateOfShift) {
        this.dateOfShift = dateOfShift;
    }

    public EmployeeViewModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeViewModel employee) {
        this.employee = employee;
    }

    public MachineViewModel getMachineViewModel() {
        return machineViewModel;
    }

    public void setMachineViewModel(MachineViewModel machineViewModel) {
        this.machineViewModel = machineViewModel;
    }

    public ElementViewModel getElement() {
        return element;
    }

    public void setElement(ElementViewModel element) {
        this.element = element;
    }

    public AlloyViewModel getAlloy() {
        return alloy;
    }

    public void setAlloy(AlloyViewModel alloy) {
        this.alloy = alloy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
