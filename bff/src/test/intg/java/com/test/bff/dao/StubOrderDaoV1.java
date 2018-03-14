package com.test.bff.dao;

import com.test.bff.configuration.RestEndpointProperties;
import com.test.bff.exception.DaoException;
import com.test.bff.model.Order;
import com.test.bff.model.Orders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StubOrderDaoV1 extends OrderDaoV1 {
    private Map<String, Orders> ordersMap = new HashMap<>();
    private Map<String, DaoException> exceptionMap = new HashMap<>();
    private String shopId;

    public StubOrderDaoV1(RestTemplate restTemplate,
                          RestEndpointProperties restEndpointProperties) {
        super(restTemplate, restEndpointProperties);
    }

    public StubOrderDaoV1 givenShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public StubOrderDaoV1 withOrders(Orders orders) {
        ordersMap.put(this.shopId, orders);
        return this;
    }

    public StubOrderDaoV1 withOrders(Order... orders) {
        List<Order> orderList = Arrays.stream(orders).collect(Collectors.toList());
        ordersMap.put(this.shopId, new Orders().withOrders(orderList));
        return this;
    }

    public StubOrderDaoV1 withException(DaoException e) {
        exceptionMap.put(this.shopId, e);
        return this;
    }

    public StubOrderDaoV1 andShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public StubOrderDaoV1 reset() {
        ordersMap.clear();
        exceptionMap.clear();
        shopId = null;
        return this;
    }

    @Override
    public Orders retrieveOrdersForShop(String shopId) throws DaoException {
        if (exceptionMap.get(shopId) != null) {
            throw exceptionMap.get(shopId);
        }
        return ordersMap.get(shopId) == null ? new Orders() : ordersMap.get(shopId);
    }
}
