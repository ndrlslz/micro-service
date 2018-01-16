package com.test.exception;

public class DaoException extends Exception {
    private String serviceEndpoint;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(Throwable throwable, String message) {
        super(message, throwable);
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }
}
