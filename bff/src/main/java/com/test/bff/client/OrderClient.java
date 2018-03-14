package com.test.bff.client;

import com.test.bff.model.Orders;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("service-order")
public interface OrderClient {
    @GetMapping("shops/{shopId}/orders")
    Orders retrieveOrdersForShop(@PathVariable("shopId") String shopId);
}
