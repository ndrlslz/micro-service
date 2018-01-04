package com.test.util;

import com.test.model.Shop;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.util.Collection;

public class ShopBuilder {
    public static Resource<Shop> shopResource(String code, String name) {
        return new Resource<>(
                new Shop(code, name)
        );
    }

    public static PagedResources<Resource<Shop>> shopPagedResources(Collection<Resource<Shop>> shopCollection) {
        return new PagedResources<>(
                shopCollection,
                new PagedResources.PageMetadata(10, 0, 0, 1)
        );
    }
}
