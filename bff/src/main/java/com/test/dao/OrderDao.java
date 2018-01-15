package com.test.dao;

import com.test.configuration.RestEndpointProperties;
import com.test.exception.ExceptionHandler;
import com.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Repository
public class OrderDao {
    private RestTemplate restTemplate;
    private RestEndpointProperties restEndpointProperties;
    private String baseUrl;

    @Autowired
    public OrderDao(RestTemplate restTemplate,
                    @Qualifier("serviceOrderProperties") RestEndpointProperties restEndpointProperties) {
        this.restTemplate = restTemplate;
        this.restEndpointProperties = restEndpointProperties;
        baseUrl = format("%s:%s", restEndpointProperties.getUrl(), restEndpointProperties.getPort());
    }


    public void test() {
        Orders orders;
        System.out.println(baseUrl);
        try {
            orders = restTemplate.getForObject(baseUrl + "/shops/1/orders", Orders.class);
            orders.getOrders().forEach(order -> System.out.println(order.getVehicleName() + ", " + order.getPrice()));
        } catch (Exception exception) {
            ExceptionHandler.handle(exception);
        }
    }
}
