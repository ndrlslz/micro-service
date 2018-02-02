package com.test.dao;

import com.test.configuration.RestEndpointProperties;
import com.test.model.Vehicles;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VehicleDaoV1Test {
    private RestTemplate restTemplate;
    private VehicleDaoV1 vehicleDao;

    @Before
    public void setUp() throws Exception {
        restTemplate = Mockito.mock(RestTemplate.class);

        RestEndpointProperties restEndpointProperties = new RestEndpointProperties();
        restEndpointProperties.setUrl("http://localhost:80/service-vehicle");

        vehicleDao = new VehicleDaoV1(restTemplate, restEndpointProperties);
    }

    @Test
    public void should_return_vehicles() throws Exception {
        Vehicles expected = new Vehicles();
        when(restTemplate.getForObject("http://localhost:80/service-vehicle/vehicles?shopId=1", Vehicles.class)).thenReturn(expected);

        Vehicles vehicles = vehicleDao.retrieveVehiclesForShop("1");

        assertEquals(expected, vehicles);
    }

}
