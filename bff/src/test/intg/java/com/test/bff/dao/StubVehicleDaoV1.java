package com.test.bff.dao;

import com.test.bff.configuration.RestEndpointProperties;
import com.test.bff.exception.DaoException;
import com.test.bff.model.Vehicle;
import com.test.bff.model.Vehicles;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class StubVehicleDaoV1 extends VehicleDaoV1 {
    private Map<String, Vehicles> vehiclesMap = new HashMap<>();
    private Map<String, DaoException> exceptionMap = new HashMap<>();
    private String shopId;

    public StubVehicleDaoV1(RestTemplate restTemplate,
                            RestEndpointProperties restEndpointProperties) {
        super(restTemplate, restEndpointProperties);
    }

    public StubVehicleDaoV1 givenShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public StubVehicleDaoV1 withVehicles(Vehicles Vehicles) {
        vehiclesMap.put(this.shopId, Vehicles);
        return this;
    }

    public StubVehicleDaoV1 withVehicles(Vehicle... Vehicles) {
        List<Vehicle> vehicleList = Arrays.stream(Vehicles).collect(toList());
        vehiclesMap.put(this.shopId, new Vehicles().withVehicles(vehicleList));
        return this;
    }

    public StubVehicleDaoV1 withException(DaoException e) {
        exceptionMap.put(this.shopId, e);
        return this;
    }

    public StubVehicleDaoV1 andShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public StubVehicleDaoV1 reset() {
        vehiclesMap.clear();
        exceptionMap.clear();
        shopId = null;
        return this;
    }

    @Override
    public Vehicles retrieveVehiclesForShop(String shopId) throws DaoException {
        if (exceptionMap.get(shopId) != null) {
            throw exceptionMap.get(shopId);
        }
        return vehiclesMap.get(shopId) == null ? new Vehicles() : vehiclesMap.get(shopId);
    }
}
