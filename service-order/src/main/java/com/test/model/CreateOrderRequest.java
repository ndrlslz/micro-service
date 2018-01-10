package com.test.model;

import javax.validation.constraints.Max;
import java.math.BigDecimal;

public class CreateOrderRequest {
    private String vehicle;
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
