package com.test.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.configuration.RestEndpointProperties;
import com.test.exception.DaoException;
import com.test.model.Vehicle;
import com.test.model.Vehicles;
import com.test.utils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.test.exception.DaoExceptionBuilder.newException;
import static java.lang.String.format;

@Repository
public class VehicleDaoV1 implements VehicleDao{
    private final RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public VehicleDaoV1(RestTemplate restTemplate,
                        @Qualifier("serviceVehicleProperties") RestEndpointProperties restEndpointProperties) {
        this.restTemplate = restTemplate;
        baseUrl = format("%s:%s", restEndpointProperties.getUrl(), restEndpointProperties.getPort());
    }

    @HystrixCommand(commandKey = "retrieve vehicles for shop", fallbackMethod = "emptyVehicles")
    public Vehicles retrieveVehiclesForShop(String shopId) throws DaoException {
        try {
            String url = UrlBuilder.newUrlBuilder(baseUrl).buildRetrieveVehiclesUrl(shopId);
            return restTemplate.getForObject(url, Vehicles.class);
        } catch (Exception exception) {
            throw newException(baseUrl).build(exception);
        }
    }

    public Vehicles emptyVehicles(String shopId) {
        Vehicles vehicles = new Vehicles();
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicles.setVehicles(vehicleList);
        return vehicles;
    }
}
