package com.test.controller;

import com.test.dao.OrderDao;
import com.test.dao.VehicleDao;
import com.test.model.Orders;
import com.test.model.Shop;
import com.test.model.Vehicles;
import com.test.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/test")
    public Shop test() throws Exception {
        return shopService.retrieveShop();
    }
}
