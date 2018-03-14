package com.test.bff.exception;

public class CommunicationException extends DaoException {
    public CommunicationException(String message) {
        super(message);
    }

    public CommunicationException(Throwable e, String serviceEndpoint) {
        super(e, serviceEndpoint);
    }
}
