package com.test.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class ApiErrorsBuilder {
    private List<ApiError> errors = new ArrayList<>();

    public static ApiErrorsBuilder newErrors() {
        return new ApiErrorsBuilder();
    }

    public ApiErrorsBuilder addBadRequest(String... message) {
        addError(SC_BAD_REQUEST, message);
        return this;
    }

    public ApiErrorsBuilder addNotFound(String... message) {
        addError(SC_NOT_FOUND, message);
        return this;
    }

    public ApiErrorsBuilder addError(int code, String... message) {
        List<ApiError> notFounds = Arrays
                .stream(message)
                .map(s -> new ApiError(s, code))
                .collect(toList());
        errors.addAll(notFounds);
        return this;
    }

    public ApiErrors build() {
        return new ApiErrors(errors);
    }
}
