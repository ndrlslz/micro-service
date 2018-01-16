package com.test.service;

import com.test.dao.OrderDao;
import com.test.dao.VehicleDao;
import com.test.exception.DaoException;
import com.test.exception.DaoRuntimeException;
import com.test.model.Orders;
import com.test.model.Shop;
import com.test.model.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    private VehicleDao vehicleDao;
    private OrderDao orderDao;

    @Autowired
    public ShopService(VehicleDao vehicleDao, OrderDao orderDao) {
        this.vehicleDao = vehicleDao;
        this.orderDao = orderDao;
    }

    public Shop retrieveShop() {
        try {
            Vehicles vehicles = vehicleDao.test();
            Orders orders = orderDao.test();
            Shop shop = new Shop();
            shop.setOrders(orders.getOrders());
            shop.setVehicles(vehicles.getVehicles());
            return shop;
        } catch (DaoException e) {
            throw new DaoRuntimeException(e);
        }
    }
}
