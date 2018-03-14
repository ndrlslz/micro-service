package com.test.bff.service.v2;

import com.test.bff.client.OrderClient;
import com.test.bff.client.VehicleClient;
import com.test.bff.service.ShopService;
import com.test.bff.model.Orders;
import com.test.bff.model.Shop;
import com.test.bff.model.Vehicles;
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
