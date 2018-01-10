package com.test.exception;

import java.util.ArrayList;
import java.util.List;

public class ApiErrors {
    private List<ApiError> errors;

    private ApiErrors(ApiErrorsBuilder builder) {
        this.errors = builder.errors;
    }

    public static class ApiErrorsBuilder {
        private List<ApiError> errors;

        ApiErrorsBuilder() {
            errors = new ArrayList<>();
        }

        public ApiErrorsBuilder withError(String message, int code) {
            errors.add(new ApiError(message, code));
            return this;
        }

        public ApiErrors build() {
            return new ApiErrors(this);
        }
    }

    static ApiErrorsBuilder newInstance() {
        return new ApiErrorsBuilder();
    }

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }
}
