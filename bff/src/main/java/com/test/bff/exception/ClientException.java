package com.test.bff.exception;

public class ClientException extends DaoException {
    public ClientException(String message) {
        super(message);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(Throwable throwable, String message) {
        super(throwable, message);
    }
}
