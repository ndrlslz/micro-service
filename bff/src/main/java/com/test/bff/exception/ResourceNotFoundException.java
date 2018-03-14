package com.test.bff.exception;

public class ResourceNotFoundException extends DaoException{
    public ResourceNotFoundException(String serviceEndpoint) {
        super(serviceEndpoint);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(Throwable throwable, String serviceEndpoint) {
        super(throwable, serviceEndpoint);
    }
}
