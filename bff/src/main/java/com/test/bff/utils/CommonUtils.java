package com.test.bff.utils;

import java.util.function.Consumer;

public class CommonUtils {
    public static <E> void ifNotNull(E o, Consumer<E> consumer) {
        if (o != null) {
            consumer.accept(o);
        }
    }

    public static <E extends Exception> void throwExceptionIfExceptionExists(E o) throws Exception {
        if (o != null) {
            throw o;
        }
    }
}
