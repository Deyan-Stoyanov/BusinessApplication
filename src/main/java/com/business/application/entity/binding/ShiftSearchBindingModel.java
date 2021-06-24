package com.business.application.entity.binding;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ShiftSearchBindingModel {
    private Date startDate;
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
