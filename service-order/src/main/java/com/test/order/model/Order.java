package com.test.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @ApiModelProperty(value = "Vehicle order", position = 1)
    private String vehicle;

    @ApiModelProperty(position = 2)
    private BigDecimal price;

    private String shopName;
    private String shopId;

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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Order withVehicle(String vehicle) {
        setVehicle(vehicle);
        return this;
    }

    public Order withPrice(BigDecimal price) {
        setPrice(price);
        return this;
    }

    public Order withShopName(String shopName) {
        setShopName(shopName);
        return this;
    }

    public Order withShopId(String shopId) {
        setShopId(shopId);
        return this;
    }
}
