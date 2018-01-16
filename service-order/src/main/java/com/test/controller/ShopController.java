package com.test.controller;

import com.test.entity.ShopEntity;
import com.test.exception.ApiErrors;
import com.test.model.Shops;
import com.test.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Shop", description = "Access to shop resources")
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class ShopController {
    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @ApiOperation(value = "retrieve all shops", response = Shops.class)
    @GetMapping("/shops")
    @ApiResponses({
            @ApiResponse(code = SC_BAD_REQUEST, response = ApiErrors.class, message = "Invalid query parameter"),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, response = ApiErrors.class, message = "Internal server error")
    })
    public Shops listAllShops(@PageableDefault Pageable pageable,
                              PagedResourcesAssembler<ShopEntity> assembler) {
        return shopService.retrieveAllShops(pageable, assembler);
    }
}
