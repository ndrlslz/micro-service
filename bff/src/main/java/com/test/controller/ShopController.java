package com.test.controller;

import com.test.exception.ApiErrors;
import com.test.model.Shop;
import com.test.service.ShopServiceV1;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@Api(value = "Bff", description = "Bff service")
@RequestMapping("/v1")
public class ShopController {
    private ShopServiceV1 shopServiceV1;

    @Autowired
    public ShopController(ShopServiceV1 shopServiceV1) {
        this.shopServiceV1 = shopServiceV1;
    }

    @GetMapping("/shops/{shop_id}")
    @ApiOperation("retrieve all orders and vehicles for specific shop")
    @ApiResponses({
            @ApiResponse(code = SC_BAD_REQUEST, response = ApiErrors.class, message = "Invalid query parameter"),
            @ApiResponse(code = SC_BAD_GATEWAY, response = ApiErrors.class, message = "Error return from order or vehicles service"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, response = ApiErrors.class, message = "Internal server error")
    })
    public Shop retrieveShop(@ApiParam(value = "Shop Id") @NotNull @PathVariable("shop_id") String shopId) throws Exception {
        return shopServiceV1.retrieveShop(shopId);
    }
}
