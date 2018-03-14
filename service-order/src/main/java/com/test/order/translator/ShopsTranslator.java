package com.test.order.translator;

import com.test.order.model.Shops;
import com.test.order.model.Shop;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class ShopsTranslator {
    public Shops translate(PagedResources<Resource<Shop>> shopResources) {
        return new Shops()
                .withMetadata(shopResources.getMetadata())
                .withLinks(shopResources.getLinks())
                .withShops(
                        shopResources
                                .getContent()
                                .stream()
                                .map(Resource::getContent)
                                .collect(toList()));
    }
}
