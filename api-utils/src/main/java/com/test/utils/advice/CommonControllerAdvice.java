package com.test.utils.advice;

import com.test.utils.model.ApiErrors;
import com.test.utils.model.ApiErrorsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CommonControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrors> handleConstraintViolationException(ConstraintViolationException e) {
        ApiErrorsBuilder apiErrorsBuilder = new ApiErrorsBuilder();
        e.getConstraintViolations().forEach(
                constraintViolation -> apiErrorsBuilder.addBadRequest(constraintViolation.getMessage()));
        return new ResponseEntity<>(
                apiErrorsBuilder.build(), HttpStatus.BAD_REQUEST
        );
    }
}
