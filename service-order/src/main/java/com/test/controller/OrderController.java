package com.test.controller;

import com.test.entity.OrderEntity;
import com.test.model.Orders;
import com.test.service.DefaultOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Order", description = "Access to order resources")
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class OrderController {
    private final DefaultOrderService orderService;

    @Autowired
    public OrderController(DefaultOrderService orderService) {
        this.orderService = orderService;
    }

    //What if shop is not exists? should I return shop id not found or just return empty orders.
    @GetMapping("shops/{id}/orders")
    public Orders retrieveOrders(@NotNull @PathVariable @ApiParam(value = "id", required = true) String id,
                                 @PageableDefault Pageable pageable,
                                 PagedResourcesAssembler<OrderEntity> assembler) {
        return orderService.retrieveAllOrdersByShopId(id, pageable, assembler);
    }
}
