package com.test.order.translator;

import com.test.order.entity.OrderEntity;
import com.test.order.entity.ShopEntity;
import com.test.order.model.CreateOrderRequest;
import com.test.order.model.Order;
import com.test.order.model.Orders;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

@Service
public class OrderTranslator {
    public Orders translatePagedResources(PagedResources<Resource<Order>> pagedResources) {
        return new Orders()
                .withLinks(pagedResources.getLinks())
                .withMetadata(pagedResources.getMetadata())
                .withOrders(
                        pagedResources
                                .getContent()
                                .stream()
                                .map(Resource::getContent)
                                .collect(toList()));
    }

    public OrderEntity translateOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setVehicle(order.getVehicle());
        orderEntity.setPrice(order.getPrice());
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setName(order.getShopName());
        shopEntity.setId(order.getShopId());
        orderEntity.setShop(shopEntity);
        return orderEntity;
    }

    public OrderEntity translateRequest(CreateOrderRequest request) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPrice(request.getPrice());
        orderEntity.setVehicle(request.getVehicle());
        return orderEntity;
    }

    public Order translateOrderEntity(OrderEntity orderEntity) {
        return new Order()
                .withPrice(orderEntity.getPrice())
                .withVehicle(orderEntity.getVehicle())
                .withShopName(orderEntity.getShop().getName())
                .withShopId(orderEntity.getShop().getId());
    }
}
