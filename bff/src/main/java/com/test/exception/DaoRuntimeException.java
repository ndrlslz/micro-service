package com.test.exception;

public class DaoRuntimeException extends RuntimeException {
    private DaoException daoException;

    public DaoRuntimeException(DaoException daoException) {
        this.daoException = daoException;
    }

    public DaoException getDaoException() {
        return daoException;
    }
}
