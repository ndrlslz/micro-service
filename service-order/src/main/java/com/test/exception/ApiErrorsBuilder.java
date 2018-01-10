package com.test.exception;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class ApiErrorsBuilder {
    public static ApiErrors badRequest(String message) {
        return ApiErrors.newInstance()
                .withError(message, SC_BAD_REQUEST)
                .build();
    }

    public static ApiErrors notFound(String message) {
        return ApiErrors.newInstance()
                .withError(message, SC_NOT_FOUND)
                .build();
    }
}
