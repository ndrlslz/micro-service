package com.test.service;

import com.test.model.Shop;
import com.test.entity.ShopEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

@Service
public class ShopAssembler implements ResourceAssembler<ShopEntity, Resource<Shop>> {
    @Override
    public Resource<Shop> toResource(ShopEntity entity) {
        Shop shop = new Shop(entity.getCode(), entity.getName());
        return new Resource<>(shop);
    }
}
