package com.test.controller;

import com.test.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.test.exception.ApiErrorsBuilder.newErrors;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(DaoRuntimeException.class)
    public ApiErrors handleRaoRuntimeException(DaoRuntimeException e) {
        DaoException daoException = e.getDaoException();
        if (daoException instanceof ServiceException) {
            return new ApiErrors(((ServiceException) daoException).getApiErrors());
        } else if (daoException instanceof CommunicationException) {
            return newErrors()
                    .addServiceUnavailable("communication error with downstream " + daoException.getServiceEndpoint())
                    .build();
        } else if (daoException instanceof ResourceNotFoundException) {
            return newErrors()
                    .addNotFound("Required resource not found")
                    .build();
        }
        LOGGER.error(e.getDaoException().getCause().getMessage());
        return newErrors()
                .addInternalError("Internal server error")
                .build();
    }
}
