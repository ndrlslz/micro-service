package com.test.order.validation;

import com.test.order.exception.MissingParameterException;

import static java.lang.String.format;

public class Validator {
    public static void notNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("object cannot be null");
        }
    }

    public static void notEmpty(String s, String variable) {
        if (s == null || s.length() == 0) {
            throw new MissingParameterException(format("%s cannot be null or empty", variable));
        }
    }
}
