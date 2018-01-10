package com.test.exception;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String message) {
        super(message);
    }
}
