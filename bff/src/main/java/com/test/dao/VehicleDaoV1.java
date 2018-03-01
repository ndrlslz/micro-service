package com.test.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.configuration.RestEndpointProperties;
import com.test.exception.DaoException;
import com.test.model.Vehicles;
import com.test.utils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.test.exception.DaoExceptionBuilder.newException;

@Repository
public class VehicleDaoV1 implements VehicleDao{
    private final RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public VehicleDaoV1(RestTemplate restTemplate,
                        @Qualifier("serviceVehicleProperties") RestEndpointProperties restEndpointProperties) {
        this.restTemplate = restTemplate;
        baseUrl = restEndpointProperties.getUrl();
    }

    @HystrixCommand(commandKey = "retrieve vehicles for shop")
    public Vehicles retrieveVehiclesForShop(String shopId) throws DaoException {
        try {
            String url = UrlBuilder.newUrlBuilder(baseUrl).buildRetrieveVehiclesUrl(shopId);
            return restTemplate.getForObject(url, Vehicles.class);
        } catch (Exception exception) {
            throw newException(baseUrl).build(exception);
        }
    }
}
