package com.business.application.entity.view;

import com.business.application.enumerations.ShiftType;

import java.util.Date;

public class ShiftViewModel {

    private String id;
    private ShiftType shiftType;
    private Date dateOfShift;
    private EmployeeViewModel employee;
    private MachineViewModel machine;
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

    public MachineViewModel getMachine() {
        return machine;
    }

    public void setMachine(MachineViewModel machine) {
        this.machine = machine;
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
