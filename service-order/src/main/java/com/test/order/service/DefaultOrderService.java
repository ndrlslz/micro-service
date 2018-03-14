package com.test.order.service;

import com.test.order.entity.OrderEntity;
import com.test.order.entity.ShopEntity;
import com.test.order.exception.ResourceNotFoundException;
import com.test.order.model.CreateOrderRequest;
import com.test.order.model.Order;
import com.test.order.model.Orders;
import com.test.order.repository.OrderRepository;
import com.test.order.repository.ShopRepository;
import com.test.order.translator.OrderTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

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
        return orderTranslator.translatePagedResources(resources);
    }

    @Override
    public Order createOrder(String shopId, CreateOrderRequest request) {
        ShopEntity shopEntity = shopRepository.findOne(shopId);
        if (shopEntity == null) {
            throw new ResourceNotFoundException("Shop not found");
        }
        OrderEntity orderEntity = orderTranslator.translateRequest(request);
        shopEntity.addOrder(orderEntity);
        OrderEntity save = orderRepository.save(orderEntity);
        return orderTranslator.translateOrderEntity(save);
    }
}
