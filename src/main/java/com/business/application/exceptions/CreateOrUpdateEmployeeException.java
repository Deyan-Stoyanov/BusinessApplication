package com.business.application.exceptions;

public class CreateOrUpdateEmployeeException extends Exception {

    private static final String ERROR_MESSAGE = "Error on creating employee: ";

    public CreateOrUpdateEmployeeException() {
        super(ERROR_MESSAGE);
    }
}
