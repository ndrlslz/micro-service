package com.test.controller;

import com.test.exception.ApiErrors;
import com.test.exception.MissingParameterException;
import com.test.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.test.exception.ApiErrorsBuilder.badRequest;
import static com.test.exception.ApiErrorsBuilder.notFound;

@RestControllerAdvice
public class ControllerAdvice {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingParameterException.class)
    public ApiErrors missingParameter(MissingParameterException exception) {
        return badRequest(exception.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrors resourceNotFound(ResourceNotFoundException exception) {
        return notFound(exception.getMessage());
    }
}
