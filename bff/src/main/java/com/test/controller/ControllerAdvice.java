package com.test.controller;

import com.test.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.CompletableFuture;

import static com.test.exception.ApiErrorsBuilder.newErrors;
import static javax.servlet.http.HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(DaoRuntimeException.class)
    public ResponseEntity<ApiErrors> handleRaoRuntimeException(DaoRuntimeException e) {
        DaoException daoException = e.getDaoException();
        if (daoException instanceof ServiceException) {
            return new ResponseEntity<>(
                    new ApiErrors(((ServiceException) daoException).getApiErrors()),
                    HttpStatus.SERVICE_UNAVAILABLE);
        } else if (daoException instanceof CommunicationException) {
            return new ResponseEntity<>(
                    newErrors()
                            .addServiceUnavailable("communication error with downstream " + daoException.getServiceEndpoint())
                            .build(),
                    HttpStatus.BAD_GATEWAY);
        } else if (daoException instanceof ResourceNotFoundException) {
            return new ResponseEntity<>(
                    newErrors()
                            .addNotFound("Required resource not found")
                            .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                newErrors()
                        .addInternalError("Internal server error")
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommunicationException.class)
    public void handleResourceAccess(CommunicationException e) {
        e.printStackTrace();
    }
}