package com.test.constraint;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;

public class ValidShopIdValidatorTest {
    private ValidShopIdValidator validator;
    private ConstraintValidatorContext context;

    @Before
    public void setUp() {
        validator = new ValidShopIdValidator();
        context = Mockito.mock(ConstraintValidatorContext.class);
    }

    @Test
    public void should_validate_successfully() {
        boolean result = validator.isValid("123", context);
        assertTrue(result);
    }

    @Test
    public void should_validate_unsuccessfully() {
        boolean result = validator.isValid("abc", context);
        assertTrue(!result);
    }
}