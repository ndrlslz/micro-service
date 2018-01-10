package com.test.service;

import com.test.entity.OrderEntity;
import com.test.entity.ShopEntity;
import com.test.exception.ResourceNotFoundException;
import com.test.model.CreateOrderRequest;
import com.test.model.Order;
import com.test.model.Orders;
import com.test.repository.OrderRepository;
import com.test.repository.ShopRepository;
import com.test.translator.OrderTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;

    private final OrderAssembler orderAssembler;

    private final OrderTranslator orderTranslator;

    private final ShopRepository shopRepository;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository,
                               OrderAssembler orderAssembler,
                               OrderTranslator orderTranslator,
                               ShopRepository shopRepository) {
        this.orderRepository = orderRepository;
        this.orderAssembler = orderAssembler;
        this.orderTranslator = orderTranslator;
        this.shopRepository = shopRepository;
    }

    @Override
    public Orders retrieveAllOrdersByShopId(String shopId, Pageable pageable, PagedResourcesAssembler<OrderEntity> pagedResourcesAssembler) {
        Page<OrderEntity> orderEntities = orderRepository.findAllByShopId(shopId, pageable);
        PagedResources<Resource<Order>> resources = pagedResourcesAssembler.toResource(orderEntities, orderAssembler);
        return orderTranslator.translate(resources);
    }

    //TODO 1. return exception 2. max of input, like max length of price.
    @Override
    public Order createOrder(String shopId, CreateOrderRequest request) {
        ShopEntity shopEntity = shopRepository.findOne(shopId);
        if (shopEntity == null) {
            throw new ResourceNotFoundException("Shop not found");
        }
        OrderEntity orderEntity = orderTranslator.translate(request);
        shopEntity.addOrder(orderEntity);
        OrderEntity save = orderRepository.save(orderEntity);
        return orderTranslator.translate(save);
    }
}
