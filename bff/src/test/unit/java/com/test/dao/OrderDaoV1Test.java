package com.test.dao;

import com.test.configuration.RestEndpointProperties;
import com.test.model.Orders;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class OrderDaoV1Test {
    private RestTemplate restTemplate;
    private OrderDaoV1 orderDao;

    @Before
    public void setUp() throws Exception {
        restTemplate = Mockito.mock(RestTemplate.class);

        RestEndpointProperties restEndpointProperties = new RestEndpointProperties();
        restEndpointProperties.setUrl("http://localhost:80/service-order");

        orderDao = new OrderDaoV1(restTemplate, restEndpointProperties);
    }

    @Test
    public void should_return_orders() throws Exception {
        Orders expected = new Orders();
        when(restTemplate.getForObject("http://localhost:80/service-order/shops/1/orders", Orders.class)).thenReturn(expected);

        Orders orders = orderDao.retrieveOrdersForShop("1");


        assertEquals(expected, orders);
    }
}
