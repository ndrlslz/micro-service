package com.test.translator;

import com.test.entity.OrderEntity;
import com.test.entity.ShopEntity;
import com.test.model.CreateOrderRequest;
import com.test.model.Order;
import com.test.model.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTranslator {
    public Orders translate(PagedResources<Resource<Order>> pagedResources) {
        Orders orders = new Orders();
        orders.setLinks(pagedResources.getLinks());
        orders.setMetadata(pagedResources.getMetadata());
        List<Order> orderList = new ArrayList<>();
        pagedResources.forEach(resource -> orderList.add(resource.getContent()));
        orders.setOrders(orderList);
        return orders;
    }

    public OrderEntity translate(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setVehicle(order.getVehicle());
        orderEntity.setPrice(order.getPrice());
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setName(order.getShopName());
        shopEntity.setId(order.getShopId());
        orderEntity.setShop(shopEntity);
        return orderEntity;
    }

    public OrderEntity translate(CreateOrderRequest request) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPrice(request.getPrice());
        orderEntity.setVehicle(request.getVehicle());
        return orderEntity;
    }
    public Order translate(OrderEntity orderEntity) {
        Order order = new Order();
        order.setPrice(orderEntity.getPrice());
        order.setVehicle(orderEntity.getVehicle());
        order.setShopName(orderEntity.getShop().getName());
        order.setShopId(orderEntity.getShop().getId());
        return order;
    }
}
