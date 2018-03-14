package com.test.order.exception;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String message) {
        super(message);
    }

    public MissingParameterException(String message, Throwable e) {
        super(message, e);
    }
}
