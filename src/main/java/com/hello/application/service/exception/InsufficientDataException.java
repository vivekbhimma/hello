package com.hello.application.service.exception;

public class InsufficientDataException extends ServiceException {

    private static final String INSUFFICIENT_DATA_FOUND = "Insufficient data found";

    public InsufficientDataException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getErrorMessage() {
        return INSUFFICIENT_DATA_FOUND;
    }
}
