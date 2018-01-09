package com.test.translator;

import com.test.model.Order;
import com.test.model.Orders;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTranslator {
    public Orders translate(PagedResources<Resource<Order>> pagedResources) {
        Orders orders = new Orders();
        orders.setLinks(pagedResources.getLinks());
        orders.setMetadata(pagedResources.getMetadata());
        List<Order> orderList = new ArrayList<>();
        pagedResources.forEach(resource -> orderList.add(resource.getContent()));
        orders.setOrders(orderList);
        return orders;
    }
}
