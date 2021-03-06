package com.test.order.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateOrderRequest {
    @NotNull
    private String vehicle;

    @NotNull
    @Max(value = 100000000)
    private BigDecimal price;

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
