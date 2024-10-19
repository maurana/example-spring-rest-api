package com.crud.controller.advice;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.crud.response.GeneralResponse;
import com.crud.exception.GenericErrorResponse;
import com.crud.exception.NotFoundException;
import com.crud.exception.DataExistException;
import com.crud.exception.BadRequestCustomException;

@ControllerAdvice
public class ErrorExceptionHandler {

   @ExceptionHandler(GenericErrorResponse.class)
    public ResponseEntity<?> genericError(GenericErrorResponse ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<GeneralResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errorList = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(mappingError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorList), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GeneralResponse<Object>> handleGeneralExceptions(Exception ex) {
        List<String> errorList = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mappingError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorList),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<GeneralResponse<Object>> handleRuntimeExceptions(RuntimeException ex) {
        List<String> errorList = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mappingError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorList),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataExistException.class)
    public final ResponseEntity<GeneralResponse<Object>> handleRuntimeExceptions(DataExistException ex) {
        List<String> errorList = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mappingError(HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(), errorList),
                new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<GeneralResponse<Object>> handleNotFoundException(NotFoundException ex) {
        List<String> errorList = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mappingError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                errorList), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestCustomException.class)
    public final ResponseEntity<GeneralResponse<Object>> handleBadRequestCustomException(BadRequestCustomException ex) {
        List<String> errorList = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(mappingError(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), errorList),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private GeneralResponse<Object> mappingError(int responseCode, String responseMessage, List<String> errorList) {
        return GeneralResponse.builder()
            .responseCode(responseCode)
            .responseMessage(responseMessage)
            .errorList(errorList)
            .build();
    }
}