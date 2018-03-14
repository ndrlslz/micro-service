package com.test.controller;

import com.test.log.PerformanceLog;
import com.test.model.ApiErrors;
import com.test.model.Shop;
import com.test.service.ShopAsyncServiceV1;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Bff", description = "Bff service")
@RequestMapping(value = "/v3", produces = APPLICATION_JSON_VALUE)
public class ShopControllerV3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopControllerV3.class);
    private ShopAsyncServiceV1 shopService;

    @Autowired
    public ShopControllerV3(ShopAsyncServiceV1 shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops/{shop_id}")
    @ApiOperation("retrieve all orders and vehicles for specific shop")
    @ApiResponses({
            @ApiResponse(code = SC_BAD_REQUEST, response = ApiErrors.class, message = "Invalid query parameter"),
            @ApiResponse(code = SC_BAD_GATEWAY, response = ApiErrors.class, message = "Error return from order or vehicles service"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, response = ApiErrors.class, message = "Internal server error")
    })
    @PerformanceLog
    public Shop retrieveShop(@ApiParam(value = "Shop Id") @NotNull @PathVariable("shop_id") String shopId) throws Exception {
        LOGGER.info("retrieve v3 shop for shop id" + shopId);

        return shopService.retrieveShop(shopId);
    }

}

