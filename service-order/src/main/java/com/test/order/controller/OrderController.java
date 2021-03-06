package com.test.order.controller;

import com.test.utils.constraint.ValidShopId;
import com.test.order.entity.OrderEntity;
import com.test.utils.log.PerformanceLog;
import com.test.utils.model.ApiErrors;
import com.test.order.model.CreateOrderRequest;
import com.test.order.model.Order;
import com.test.order.model.Orders;
import com.test.order.service.DefaultOrderService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.test.order.validation.OrderValidation.validateCreateOrderRequest;
import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Order", description = "Access to order resources")
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@Validated
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final DefaultOrderService orderService;

    @Autowired
    public OrderController(DefaultOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("shops/{id}/orders")
    @ApiOperation("retrieve all orders for specific shop")
    @ApiResponses({
            @ApiResponse(code = SC_BAD_REQUEST, response = ApiErrors.class, message = "Invalid query parameter"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, response = ApiErrors.class, message = "Internal server error")
    })
    @PerformanceLog
    public Orders retrieveOrders(@PathVariable @ValidShopId @ApiParam(value = "id", required = true) String id,
                                 @PageableDefault Pageable pageable,
                                 PagedResourcesAssembler<OrderEntity> assembler) {
        LOGGER.info("retrieve orders for shop id " + id);

        return orderService.retrieveAllOrdersByShopId(id, pageable, assembler);
    }

    @PostMapping("shops/{id}/order")
    @ApiOperation("create a new order")
    @ApiResponses({
            @ApiResponse(code = SC_NOT_FOUND, response = ApiErrors.class, message = "Shop not found"),
            @ApiResponse(code = SC_BAD_REQUEST, response = ApiErrors.class, message = "Invalid query parameter"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, response = ApiErrors.class, message = "Internal server error")
    })
    @PerformanceLog
    public Order createOrder(@ValidShopId @PathVariable(value = "id") @ApiParam(value = "shop_id", required = true) String shopId,
                             @Valid @RequestBody CreateOrderRequest request) {
        validateCreateOrderRequest(request);

        return orderService.createOrder(shopId, request);
    }

}
