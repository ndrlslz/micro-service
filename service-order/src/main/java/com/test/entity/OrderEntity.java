package com.test.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "vehicle_order")
public class OrderEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    @Column(length = 64)
    private String vehicle;

    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }
}
