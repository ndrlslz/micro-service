package com.test.controller;

import com.test.entity.ShopEntity;
import com.test.model.Shops;
import com.test.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class ShopController {
    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    public Shops listAllShops(@PageableDefault Pageable pageable, PagedResourcesAssembler<ShopEntity> assembler) {
        return shopService.retrieveAllShops(pageable, assembler);
    }
}
