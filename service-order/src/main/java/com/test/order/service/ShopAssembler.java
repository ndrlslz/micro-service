package com.test.order.service;

import com.test.order.model.Shop;
import com.test.order.entity.ShopEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

@Service
public class ShopAssembler implements ResourceAssembler<ShopEntity, Resource<Shop>> {
    @Override
    public Resource<Shop> toResource(ShopEntity entity) {
        return new Resource<>(
                new Shop()
                    .withCode(entity.getCode())
                    .withName(entity.getName()));
    }
}
