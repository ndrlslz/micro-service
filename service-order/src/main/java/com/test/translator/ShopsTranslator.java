package com.test.translator;

import com.test.model.Shops;
import com.test.model.Shop;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopsTranslator {
    public Shops translate(PagedResources<Resource<Shop>> shopResources) {
        Shops shops = new Shops();
        shops.setMetadata(shopResources.getMetadata());
        shops.setLinks(shopResources.getLinks());
        List<Shop> shopList = new ArrayList<>();
        shopResources.getContent().forEach(resource -> shopList.add(resource.getContent()));
        shops.setShops(shopList);
        return shops;
    }
}
