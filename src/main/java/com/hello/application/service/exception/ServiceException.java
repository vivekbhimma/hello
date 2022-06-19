package com.hello.application.service.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {
    public ServiceException(Throwable cause) {
        super(cause);
    }

    public String getErrorMessage() {
        return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }
}
