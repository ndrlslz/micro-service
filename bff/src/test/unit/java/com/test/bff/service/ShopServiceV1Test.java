package com.test.bff.service;

import com.test.bff.dao.OrderDao;
import com.test.bff.dao.VehicleDao;
import com.test.bff.model.Orders;
import com.test.bff.model.Shop;
import com.test.bff.model.Vehicles;
import com.test.bff.service.ShopServiceV1;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.sleuth.NoOpSpanReporter;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.log.NoOpSpanLogger;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.sleuth.trace.DefaultTracer;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShopServiceV1Test {
    private VehicleDao vehicleDao;
    private OrderDao orderDao;
    private ShopServiceV1 shopService;

    @Before
    public void setUp() throws Exception {
        vehicleDao = mock(VehicleDao.class);
        orderDao = mock(OrderDao.class);
        SpanNamer spanNamer = mock(SpanNamer.class);
        TraceKeys traceKeys = new TraceKeys();
        Tracer tracer = new DefaultTracer(new AlwaysSampler(), new Random(), spanNamer, new NoOpSpanLogger(), new NoOpSpanReporter(), traceKeys);
        shopService = new ShopServiceV1(vehicleDao, orderDao, tracer, traceKeys, spanNamer);
    }

    @Test
    public void should_return_shop() throws Exception {
        Vehicles expectedVehicles = new Vehicles();
        Orders expectedOrders = new Orders();
        Shop expected = new Shop();
        expected.setOrders(expectedOrders.getOrders());
        expected.setVehicles(expectedVehicles.getVehicles());
        when(vehicleDao.retrieveVehiclesForShop("1")).thenReturn(expectedVehicles);
        when(orderDao.retrieveOrdersForShop("1")).thenReturn(expectedOrders);

        Shop shop = shopService.retrieveShop("1");

        assertEquals(expectedVehicles.getVehicles(), shop.getVehicles());
        assertEquals(expectedOrders.getOrders(), shop.getOrders());
    }
}
