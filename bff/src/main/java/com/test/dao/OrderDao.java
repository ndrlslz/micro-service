package com.test.dao;

import com.test.configuration.RestEndpointProperties;
import com.test.exception.DaoException;
import com.test.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.test.exception.DaoExceptionBuilder.newException;
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

    public Orders retrieveOrdersForShop(String shopId) throws DaoException {
        try {
            return restTemplate.getForObject(baseUrl + "/shops/" + shopId + "/orders", Orders.class);
        } catch (Exception exception) {
            throw newException(baseUrl).build(exception);
        }
    }
}
