package com.test.service.v2;

import com.test.dao.OrderDao;
import com.test.dao.VehicleDao;
import com.test.exception.DaoException;
import com.test.exception.DaoRuntimeException;
import com.test.model.Orders;
import com.test.model.Shop;
import com.test.model.Vehicles;
import com.test.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceV2 implements ShopService{
    private VehicleDao vehicleDaoV2;
    private OrderDao orderDaoV2;

    @Autowired
    public ShopServiceV2(@Qualifier("vehicleDaoV2") VehicleDao vehicleDaoV2,
                       @Qualifier("orderDaoV2") OrderDao orderDaoV2) {
        this.vehicleDaoV2 = vehicleDaoV2;
        this.orderDaoV2 = orderDaoV2;
    }

    public Shop retrieveShop(String shopId) {
        try {
            Vehicles vehicles = vehicleDaoV2.retrieveVehiclesForShop(shopId);
            Orders orders = orderDaoV2.retrieveOrdersForShop(shopId);
            return new Shop()
                    .withOrders(orders.getOrders())
                    .withVehicles(vehicles.getVehicles());
        } catch (DaoException e) {
            throw new DaoRuntimeException(e);
        }
    }

}
