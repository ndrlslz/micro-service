package com.test.service;

import com.test.dao.OrderDao;
import com.test.dao.OrderDaoV1;
import com.test.dao.VehicleDao;
import com.test.dao.VehicleDaoV1;
import com.test.exception.DaoException;
import com.test.exception.DaoRuntimeException;
import com.test.model.Orders;
import com.test.model.Shop;
import com.test.model.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceV1 implements ShopService{
    private VehicleDao vehicleDaoV1;
    private OrderDao orderDaoV1;

    @Autowired
    public ShopServiceV1(@Qualifier("vehicleDaoV1") VehicleDao vehicleDaoV1,
                         @Qualifier("orderDaoV1") OrderDao orderDaoV1) {
        this.vehicleDaoV1 = vehicleDaoV1;
        this.orderDaoV1 = orderDaoV1;
    }

    public Shop retrieveShop(String shopId) {
        try {
            Vehicles vehicles = vehicleDaoV1.retrieveVehiclesForShop(shopId);
            Orders orders = orderDaoV1.retrieveOrdersForShop(shopId);
            Shop shop = new Shop();
            shop.setOrders(orders.getOrders());
            shop.setVehicles(vehicles.getVehicles());
            return shop;
        } catch (DaoException e) {
            throw new DaoRuntimeException(e);
        }
    }
}
