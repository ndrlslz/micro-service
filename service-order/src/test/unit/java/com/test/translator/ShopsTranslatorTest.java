package com.test.translator;

import com.test.model.Shop;
import com.test.model.Shops;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopsTranslatorTest {
    private ShopsTranslator shopsTranslator;

    @Before
    public void setUp() throws Exception {
        shopsTranslator = new ShopsTranslator();
    }

    @Test
    public void should_return_shops() throws Exception {
        List<Resource<Shop>> shopCollection = Arrays.asList(
                new Resource<>(new Shop("code1", "name1"), new Link("test")),
                new Resource<>(new Shop("code2", "name2"), new Link("test"))
        );
        PagedResources<Resource<Shop>> resources = new PagedResources<>(
                shopCollection,
                new PagedResources.PageMetadata(10, 0, 0, 1),
                new Link("test")
        );

        Shops shops = shopsTranslator.translate(resources);

        assertEquals(2, shops.getShops().size());
        assertEquals("name1", shops.getShops().get(0).getName());
        assertEquals("code1", shops.getShops().get(0).getCode());
        assertEquals("code2", shops.getShops().get(1).getCode());
        assertEquals("name2", shops.getShops().get(1).getName());
    }
}