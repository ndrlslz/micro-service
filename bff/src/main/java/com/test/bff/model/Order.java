package com.test.bff.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    @JsonProperty("vehicle")
    private String vehicleName;

    private String price;
    private String shopName;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Order withVehicleName(String vehicleName) {
        setVehicleName(vehicleName);
        return this;
    }

    public Order withPrice(String price) {
        setPrice(price);
        return this;
    }

    public Order withShopName(String shopName) {
        setShopName(shopName);
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "vehicleName='" + vehicleName + '\'' +
                ", price='" + price + '\'' +
                ", shopName='" + shopName + '\'' +
                '}';
    }
}
