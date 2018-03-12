package com.test.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class DaoExceptionBuilder {
    private String destinationEndpoint;

    private DaoExceptionBuilder(String destinationEndpoint) {
        this.destinationEndpoint = destinationEndpoint;
    }

    public static DaoExceptionBuilder newException(String destinationEndpoint) {
        return new DaoExceptionBuilder(destinationEndpoint);
    }

    public DaoException build(Exception exception) {
        DaoException daoException = translateException(exception);
        daoException.setServiceEndpoint(destinationEndpoint);
        return daoException;
    }

    private DaoException translateException(Exception exception) {
        if (exception instanceof RestClientException) {
            return translateRestClientException((RestClientException) exception);
        }
        return new DaoException(exception, exception.getMessage());
    }

    private DaoException translateRestClientException(RestClientException exception) {
        if (exception instanceof ResourceAccessException) {
            return new CommunicationException(exception, exception.getMessage());
        } else if (exception instanceof HttpStatusCodeException) {
            return translateHttpStatusCodeException((HttpStatusCodeException) exception);
        }
        return new DaoException(exception, exception.getMessage());
    }

    private DaoException translateHttpStatusCodeException(HttpStatusCodeException exception) {
        HttpStatus statusCode = exception.getStatusCode();
        if (statusCode.equals(HttpStatus.NOT_FOUND)) {
            return new ResourceNotFoundException(exception, exception.getMessage());
        }
        try {
            ApiErrors apiErrors = new ObjectMapper().readValue(exception.getResponseBodyAsString(), ApiErrors.class);
            return new ServiceException(exception, apiErrors.getErrors());
        } catch (IOException e) {
            return new ClientException(e, e.getMessage());
        }
    }
}
