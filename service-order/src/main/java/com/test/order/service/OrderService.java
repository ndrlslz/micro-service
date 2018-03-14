package com.test.order.service;

import com.test.order.entity.OrderEntity;
import com.test.order.model.CreateOrderRequest;
import com.test.order.model.Order;
import com.test.order.model.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

public interface OrderService {
    Orders retrieveAllOrdersByShopId(String shopId, Pageable pageable, PagedResourcesAssembler<OrderEntity> pagedResourcesAssembler);

    Order createOrder(String shopId, CreateOrderRequest request);
}
