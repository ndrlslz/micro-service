package com.test.service;

import com.test.entity.OrderEntity;
import com.test.model.Order;
import com.test.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

public interface OrderService {
    Orders retrieveAllOrdersByShopId(String shopId, Pageable pageable, PagedResourcesAssembler<OrderEntity> pagedResourcesAssembler);
}
