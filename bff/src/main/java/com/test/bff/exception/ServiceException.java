package com.test.bff.exception;

import com.test.utils.model.ApiError;

import java.util.List;

public class ServiceException extends DaoException {
    private List<ApiError> apiErrors;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(Throwable throwable, String message) {
        super(throwable, message);
    }

    public ServiceException(Throwable throwable, List<ApiError> apiErrors) {
        super(throwable);
        this.apiErrors = apiErrors;
    }

    public List<ApiError> getApiErrors() {
        return apiErrors;
    }
}
