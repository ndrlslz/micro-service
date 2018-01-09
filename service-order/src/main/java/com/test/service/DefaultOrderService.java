package com.test.service;

import com.test.entity.OrderEntity;
import com.test.model.Order;
import com.test.model.Orders;
import com.test.repository.OrderRepository;
import com.test.translator.OrderTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrderService implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderAssembler orderAssembler;

    @Autowired
    private OrderTranslator orderTranslator;

    @Override
    public Orders retrieveAllOrdersByShopId(String shopId, Pageable pageable, PagedResourcesAssembler<OrderEntity> pagedResourcesAssembler) {
        Page<OrderEntity> orderEntities = orderRepository.findAllByShopId(shopId, pageable);
        PagedResources<Resource<Order>> resources = pagedResourcesAssembler.toResource(orderEntities, orderAssembler);
        return orderTranslator.translate(resources);
    }
}
