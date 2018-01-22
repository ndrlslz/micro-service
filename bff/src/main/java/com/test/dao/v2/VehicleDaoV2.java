package com.test.dao.v2;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.dao.VehicleDao;
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

@Repository
public class VehicleDaoV2 implements VehicleDao {
    private final RestTemplate restTemplate;
    private final static String BASE_URL = "http://service-vehicle";

    @Autowired
    public VehicleDaoV2(@Qualifier("restTemplateV2") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(commandKey = "retrieve vehicles for shop v2", fallbackMethod = "emptyVehicles")
    public Vehicles retrieveVehiclesForShop(String shopId) throws DaoException {
        try {
            String url = UrlBuilder.newUrlBuilder(BASE_URL).buildRetrieveVehiclesUrl(shopId);
            return restTemplate.getForObject(url, Vehicles.class);
        } catch (Exception exception) {
            throw newException(BASE_URL).build(exception);
        }
    }

    public Vehicles emptyVehicles(String shopId) {
        Vehicles vehicles = new Vehicles();
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicles.setVehicles(vehicleList);
        return vehicles;
    }
}
