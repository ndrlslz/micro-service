package com.test.service;

import com.test.model.Order;
import org.springframework.data.domain.Page;

public interface OrderService {
    Page<Order> retrieveAllOrders();
}
