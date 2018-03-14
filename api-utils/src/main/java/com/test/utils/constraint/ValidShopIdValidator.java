package com.test.utils.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidShopIdValidator implements ConstraintValidator<ValidShopId, String> {
    private static final String NUMBER_REGEX = "[0-9]+";

    @Override
    public void initialize(ValidShopId constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(NUMBER_REGEX);
    }
}
