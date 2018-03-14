package com.test.utils.model;

import java.util.List;

import static java.util.Arrays.asList;

public class ApiErrors {
    private List<ApiError> errors;

    public ApiErrors() {

    }

    public ApiErrors(List<ApiError> errors) {
        this.errors = errors;
    }

    public ApiErrors(ApiError... errors) {
        this.errors = asList(errors);
    }

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }
}
