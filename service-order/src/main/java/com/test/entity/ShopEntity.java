package com.test.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shop")
public class ShopEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String code;
    private String name;

    @OneToMany(mappedBy = "shop")
    private Set<OrderEntity> orders;

    public void addOrder(OrderEntity orderEntity) {
        this.getOrders().add(orderEntity);
        orderEntity.setShop(this);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
