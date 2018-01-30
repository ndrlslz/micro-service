package com.test.configuratioin;

import com.test.configuration.RestEndpointProperties;
import com.test.dao.OrderDaoV1;
import com.test.dao.StubOrderDaoV1;
import com.test.dao.StubVehicleDaoV1;
import com.test.dao.VehicleDaoV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class IntegrationTestConfiguration {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("serviceOrderProperties")
    private RestEndpointProperties serviceOrderEndpointProperties;

    @Autowired
    @Qualifier("serviceVehicleProperties")
    private RestEndpointProperties serviceVehicleEndpointProperties;

    @Bean
    public OrderDaoV1 orderDaoV1() {
        return new StubOrderDaoV1(restTemplate, serviceOrderEndpointProperties);
    }

    @Bean
    public VehicleDaoV1 vehicleDaoV1() {
        return new StubVehicleDaoV1(restTemplate, serviceVehicleEndpointProperties);
    }
}
