package com.test.util;

import com.test.model.Shop;
import com.test.model.Shops;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ShopBuilder {
    public static Resource<Shop> shopResource(String code, String name) {
        Shop shop = new Shop();
        shop.setCode(code);
        shop.setName(name);
        return new Resource<>(shop);
    }

    public static PagedResources<Resource<Shop>> shopPagedResources(Collection<Resource<Shop>> shopCollection) {
        return new PagedResources<>(
                shopCollection,
                new PagedResources.PageMetadata(10, 0, 0, 1)
        );
    }

    public static Shops shops(String... pair) {
        assert pair.length % 2 == 0;
        List<Shop> shopList = new ArrayList<>();
        for (int i = 0; i < pair.length; i = i + 2) {
            Shop shop = new Shop();
            shop.setCode(pair[i]);
            shop.setName(pair[i + 1]);
            shopList.add(shop);
        }

        Shops shops = new Shops();
        shops.setShops(shopList);
        return shops;
    }
}
