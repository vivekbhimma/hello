package com.hello.application.service.exception;

public class ThrottledException extends ServiceException {
    public ThrottledException(Throwable cause) {
        super(cause);
    }
}
