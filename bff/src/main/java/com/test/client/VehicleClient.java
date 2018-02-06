package com.test.client;

import com.test.model.Vehicles;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-vehicle")
public interface VehicleClient {
    @GetMapping("vehicles")
    Vehicles retrieveVehiclesForShop(@RequestParam("shopId") String shopId);
}
