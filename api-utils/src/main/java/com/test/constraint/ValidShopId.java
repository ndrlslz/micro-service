package com.test.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidShopIdValidator.class)
public @interface ValidShopId {
    String message() default "{com.test.constraint.ValidShopId.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
