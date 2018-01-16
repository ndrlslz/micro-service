package com.test.controller;

import com.test.entity.OrderEntity;
import com.test.exception.MissingParameterException;
import com.test.exception.ResourceNotFoundException;
import com.test.model.CreateOrderRequest;
import com.test.model.Order;
import com.test.model.Orders;
import com.test.service.DefaultOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.test.validation.OrderValidation.validateCreateOrderRequest;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Order", description = "Access to order resources")
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@Validated
public class OrderController {
    private final DefaultOrderService orderService;

    @Autowired
    public OrderController(DefaultOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("shops/{id}/orders")
    public Orders retrieveOrders(@NotNull @PathVariable @ApiParam(value = "id", required = true) String id,
                                 @PageableDefault Pageable pageable,
                                 PagedResourcesAssembler<OrderEntity> assembler) {
        return orderService.retrieveAllOrdersByShopId(id, pageable, assembler);
    }

    @PostMapping("shops/{id}/order")
    public Order createOrder(@NotNull @PathVariable(value = "id") @ApiParam(value = "shop_id", required = true) String shopId,
                             @Valid @RequestBody CreateOrderRequest request) {
        validateCreateOrderRequest(request);

        return orderService.createOrder(shopId, request);
    }

}
