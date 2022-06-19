package com.hello.application.service.exception;

public class InvalidPostalCodeException extends ServiceException {

    private static final String INVALID_INPUT_POSTCODE = "Invalid input: postcode";

    public InvalidPostalCodeException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getErrorMessage() {
        return INVALID_INPUT_POSTCODE;
    }
}
