package com.test.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.configuration.RestEndpointProperties;
import com.test.exception.DaoException;
import com.test.model.Orders;
import com.test.utils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static com.test.exception.DaoExceptionBuilder.newException;

@Repository
public class OrderDaoV1 implements OrderDao {
    private RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public OrderDaoV1(RestTemplate restTemplate,
                      @Qualifier("serviceOrderProperties") RestEndpointProperties restEndpointProperties) {
        this.restTemplate = restTemplate;
        baseUrl = restEndpointProperties.getUrl();
    }

    @HystrixCommand(commandKey = "retrieve orders for shop")
    public Orders retrieveOrdersForShop(String shopId) throws DaoException {
        try {
            String url = UrlBuilder.newUrlBuilder(baseUrl).buildRetrieveOrdersUrl(shopId);
            return restTemplate.getForObject(url, Orders.class);
        } catch (Exception exception) {
            throw newException(baseUrl).build(exception);
        }
    }

}
