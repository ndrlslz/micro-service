package com.test.translator;

import com.test.model.Shops;
import com.test.model.Shop;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
