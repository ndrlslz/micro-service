package com.test.service.v2;

import com.test.client.OrderClient;
import com.test.client.VehicleClient;
import com.test.model.Orders;
import com.test.model.Shop;
import com.test.model.Vehicles;
import com.test.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceV2 implements ShopService {
    private OrderClient orderClient;
    private VehicleClient vehicleClient;

    @Autowired
    public ShopServiceV2(VehicleClient vehicleClient,
                         OrderClient orderClient) {
        this.vehicleClient = vehicleClient;
        this.orderClient = orderClient;
    }

    public Shop retrieveShop(String shopId) {
        Vehicles vehicles = vehicleClient.retrieveVehiclesForShop(shopId);
        Orders orders = orderClient.retrieveOrdersForShop(shopId);
        return new Shop()
                .withOrders(orders.getOrders())
                .withVehicles(vehicles.getVehicles());
    }

}
