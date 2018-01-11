package com.test.controller;

import com.test.exception.ApiErrors;
import com.test.exception.ApiErrorsBuilder;
import com.test.exception.MissingParameterException;
import com.test.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.test.exception.ApiErrorsBuilder.newErrors;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvice {
    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MissingParameterException.class)
    public ApiErrors missingParameter(MissingParameterException exception) {
        return newErrors()
                .addBadRequest(exception.getMessage())
                .build();
    }

    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrors resourceNotFound(ResourceNotFoundException exception) {
        return newErrors()
                .addNotFound(exception.getMessage())
                .build();
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrors test(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        ApiErrorsBuilder errorsBuilder = ApiErrorsBuilder.newErrors();
        fieldErrors.forEach(fieldError ->
                errorsBuilder.addBadRequest(format("%s %s", fieldError.getField(), fieldError.getDefaultMessage())));
        return errorsBuilder.build();
    }

}
