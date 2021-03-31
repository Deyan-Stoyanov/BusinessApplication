package com.business.application.exceptions;

public class CreateAccountException extends Exception{

    private static final String ERROR_MESSAGE = "Error on creating account: ";

    public CreateAccountException() {
        super(ERROR_MESSAGE);
    }

}
