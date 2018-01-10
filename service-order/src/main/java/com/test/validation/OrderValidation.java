package com.test.validation;

import com.test.model.CreateOrderRequest;

import static com.test.validation.Validator.notEmpty;
import static com.test.validation.Validator.notNull;

public class OrderValidation {
    public static void validateCreateOrderRequest(CreateOrderRequest request) {
        notNull(request.getPrice());
        notEmpty(request.getVehicle(), "vehicle");
    }
}
