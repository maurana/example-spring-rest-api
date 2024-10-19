package com.crud.exception;

import lombok.Getter;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class GenericErrorResponse extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public GenericErrorResponse(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
