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

@Repository
public class OrderAsyncDaoV1 implements OrderDao {
    private RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public OrderAsyncDaoV1(RestTemplate restTemplate,
                           @Qualifier("serviceOrderProperties") RestEndpointProperties restEndpointProperties) {
        this.restTemplate = restTemplate;
        baseUrl = restEndpointProperties.getUrl();
    }

    @Override
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
