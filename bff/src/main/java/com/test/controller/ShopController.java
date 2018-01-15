package com.test.controller;

import com.test.dao.OrderDao;
import com.test.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    private VehicleDao vehicleDao;
    private OrderDao orderDao;

    @Autowired
    public ShopController(VehicleDao vehicleDao, OrderDao orderDao) {
        this.vehicleDao = vehicleDao;
        this.orderDao = orderDao;
    }

    @RequestMapping("/test")
    public void test() {
        vehicleDao.test();
        orderDao.test();
    }
}
