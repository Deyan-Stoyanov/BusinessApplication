package com.business.application.enumerations;

public enum ShiftType {
    FIRST("1"), SECOND("2"), THIRD("3");

    public final String value;

    private ShiftType(String value) {
        this.value = value;
    }
}
