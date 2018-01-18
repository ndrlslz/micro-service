package com.test.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.configuration.RestEndpointProperties;
import com.test.exception.DaoException;
import com.test.model.Order;
import com.test.model.Orders;
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

    @HystrixCommand(commandKey = "retrieve orders for shop", fallbackMethod = "emptyOrders")
    public Orders retrieveOrdersForShop(String shopId) throws DaoException {
        try {
            String url = UrlBuilder.newUrlBuilder(baseUrl).buildRetrieveOrdersUrl(shopId);
            return restTemplate.getForObject(url, Orders.class);
        } catch (Exception exception) {
            throw newException(baseUrl).build(exception);
        }
    }

    public Orders emptyOrders(String shopId) {
        List<Order> orderList = new ArrayList<>();
        Orders orders = new Orders();
        orders.setOrders(orderList);
        return orders;
    }
}
