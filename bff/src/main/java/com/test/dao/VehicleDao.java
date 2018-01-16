package com.test.dao;

import com.test.configuration.RestEndpointProperties;
import com.test.exception.DaoException;
import com.test.model.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.test.exception.DaoExceptionBuilder.newException;
import static java.lang.String.format;

@Repository
public class VehicleDao {
    private final RestTemplate restTemplate;
    private final RestEndpointProperties restEndpointProperties;
    private String baseUrl;

    @Autowired
    public VehicleDao(RestTemplate restTemplate,
                      @Qualifier("serviceVehicleProperties") RestEndpointProperties restEndpointProperties) {
        this.restTemplate = restTemplate;
        this.restEndpointProperties = restEndpointProperties;
        baseUrl = format("%s:%s", restEndpointProperties.getUrl(), restEndpointProperties.getPort());
    }

    public Vehicles test() throws DaoException {
        try {
            return restTemplate.getForObject(baseUrl + "/vehicles?shopId=1", Vehicles.class);
        } catch (Exception exception) {
            throw newException(baseUrl).build(exception);
        }
    }
}
