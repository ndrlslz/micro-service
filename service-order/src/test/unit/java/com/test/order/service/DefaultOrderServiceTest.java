package com.test.order.service;

import com.test.order.exception.ResourceNotFoundException;
import com.test.order.model.CreateOrderRequest;
import com.test.order.repository.OrderRepository;
import com.test.order.repository.ShopRepository;
import com.test.order.service.DefaultOrderService;
import com.test.order.service.OrderAssembler;
import com.test.order.service.OrderService;
import com.test.order.translator.OrderTranslator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultOrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ShopRepository shopRepository;

    private OrderService orderService;
    private OrderAssembler orderAssembler;
    private OrderTranslator orderTranslator;

    @Before
    public void setUp() {
        initMocks(this);
        orderService = new DefaultOrderService(orderRepository, orderAssembler, orderTranslator, shopRepository);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_resource_not_found_exception() {
        when(shopRepository.findOne(anyString())).thenReturn(null);

        orderService.createOrder("1", new CreateOrderRequest());
    }
}