package com.byteworks.devops.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class DataPersistFailureException extends RuntimeException {
    public DataPersistFailureException(String message) {
        super(message);
    }
}
