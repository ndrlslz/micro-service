package com.test.entity;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    private String id;
    private String vehicle;
    private String price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }
}
