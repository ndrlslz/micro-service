package com.test.exception;

import org.springframework.web.client.RestClientException;

public class ExceptionHandler {
    public static void handle(Exception exception) {
        if (exception instanceof RestClientException) {

        }
    }
}
