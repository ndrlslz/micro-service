package com.test.order.service;

import com.test.order.entity.OrderEntity;
import com.test.order.model.Order;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

@Service
public class OrderAssembler implements ResourceAssembler<OrderEntity, Resource<Order>> {
    @Override
    public Resource<Order> toResource(OrderEntity entity) {
        return new Resource<>(
                new Order()
                        .withPrice(entity.getPrice())
                        .withVehicle(entity.getVehicle())
                        .withShopName(entity.getShop().getName()));
    }
}
