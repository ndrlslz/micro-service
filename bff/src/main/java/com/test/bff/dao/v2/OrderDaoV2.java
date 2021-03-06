package com.test.bff.dao.v2;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.bff.dao.OrderDao;
import com.test.bff.exception.DaoException;
import com.test.bff.model.Order;
import com.test.bff.model.Orders;
import com.test.bff.utils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.test.bff.exception.DaoExceptionBuilder.newException;

@Repository
public class OrderDaoV2 implements OrderDao {
    private RestTemplate restTemplate;
    private final static String BASE_URL = "http://service-order";

    @Autowired
    public OrderDaoV2(@Qualifier("restTemplateV2") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(commandKey = "retrieve orders for shop v2", fallbackMethod = "emptyOrders")
    public Orders retrieveOrdersForShop(String shopId) throws DaoException {
        try {
            String url = UrlBuilder.newUrlBuilder(BASE_URL).buildRetrieveOrdersUrl(shopId);
            return restTemplate.getForObject(url, Orders.class);
        } catch (Exception exception) {
            throw newException(BASE_URL).build(exception);
        }
    }

    public Orders emptyOrders(String shopId) {
        List<Order> orderList = new ArrayList<>();
        Orders orders = new Orders();
        orders.setOrders(orderList);
        return orders;
    }

}
