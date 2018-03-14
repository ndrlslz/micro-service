package com.test.order.validation;

import com.test.order.model.CreateOrderRequest;

import static com.test.order.validation.Validator.notEmpty;
import static com.test.order.validation.Validator.notNull;

public class OrderValidation {
    public static void validateCreateOrderRequest(CreateOrderRequest request) {
        notNull(request.getPrice());
        notEmpty(request.getVehicle(), "vehicle");
    }
}
