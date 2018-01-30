package com.test.service;

import com.test.dao.OrderDao;
import com.test.dao.VehicleDao;
import com.test.model.Orders;
import com.test.model.Shop;
import com.test.model.Vehicles;
import org.junit.Before;
import org.junit.Test;

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

        shopService = new ShopServiceV1(vehicleDao, orderDao);
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
