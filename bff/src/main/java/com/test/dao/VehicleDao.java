package com.test.dao;

import com.test.configuration.RestEndpointProperties;
import com.test.model.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class VehicleDao {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("serviceVehicleProperties")
    private RestEndpointProperties restEndpointProperties;

    public void test() {
        System.out.println(restEndpointProperties.getUrl());
        Vehicles vehicles = restTemplate.getForObject("http://localhost:8764/vehicles?shopId=1", Vehicles.class);
        vehicles.getVehicles().forEach(vehicle -> System.out.println(vehicle.getName() + ", " + vehicle.getPrice()));
    }
}
