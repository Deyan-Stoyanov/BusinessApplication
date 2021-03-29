package com.business.application.entity;

import com.business.application.enumerations.ShiftType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "shifts")
public class Shift {

    private ShiftType shiftType;
    private Integer hitCount;
    private Integer wasteCount;
    private Integer barCount;
    private Date dateOfShift;
    private Employee employee;
    private Machine machine;
    private Element element;
    private Alloy alloy;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "shiftType", nullable = false)
    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    @NotBlank
    @Column(name = "hitCount", nullable = false)
    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    @NotBlank
    @Column(name = "wasteCount", nullable = false)
    public Integer getWasteCount() {
        return wasteCount;
    }

    public void setWasteCount(Integer wasteCount) {
        this.wasteCount = wasteCount;
    }

    @NotBlank
    @Column(name = "barCount", nullable = false)
    public Integer getBarCount() {
        return barCount;
    }

    public void setBarCount(Integer barCount) {
        this.barCount = barCount;
    }

    @NotBlank
    @Column(name = "dateOfShift", nullable = false)
    public Date getDateOfShift() {
        return dateOfShift;
    }

    public void setDateOfShift(Date dateOfShift) {
        this.dateOfShift = dateOfShift;
    }

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    @JoinColumn(name = "machineId", nullable = false)
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @ManyToOne
    @JoinColumn(name = "elementId", nullable = false)
    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @ManyToOne
    @JoinColumn(name = "alloyId", nullable = false)
    public Alloy getAlloy() {
        return alloy;
    }

    public void setAlloy(Alloy alloy) {
        this.alloy = alloy;
    }
}
