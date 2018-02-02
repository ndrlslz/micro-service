package com.test.client;

import com.test.model.Orders;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("service-order")
public interface OrderClient {
    @GetMapping("service-order/shops/{shopId}/orders")
    Orders retrieveOrdersForShop(@PathVariable("shopId") String shopId);
}
