package com.test.bff.configuratioin;

import com.test.bff.configuration.RestEndpointProperties;
import com.test.bff.dao.OrderDaoV1;
import com.test.bff.dao.StubOrderDaoV1;
import com.test.bff.dao.StubVehicleDaoV1;
import com.test.bff.dao.VehicleDaoV1;
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
