package com.test.translator;

import com.test.model.Shop;
import com.test.model.Shops;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.util.Arrays;

import static com.test.util.ShopBuilder.shopPagedResources;
import static com.test.util.ShopBuilder.shopResource;
import static org.junit.Assert.assertEquals;

public class ShopsTranslatorTest {
    private ShopsTranslator shopsTranslator;

    @Before
    public void setUp() throws Exception {
        shopsTranslator = new ShopsTranslator();
    }

    @Test
    public void should_return_shops() throws Exception {
        PagedResources<Resource<Shop>> resources = shopPagedResources(
                Arrays.asList(shopResource("code1", "name1"), shopResource("code2", "name2"))
        );
        Shops shops = shopsTranslator.translate(resources);

        assertEquals(2, shops.getShops().size());
        assertEquals("name1", shops.getShops().get(0).getName());
        assertEquals("code1", shops.getShops().get(0).getCode());
        assertEquals("code2", shops.getShops().get(1).getCode());
        assertEquals("name2", shops.getShops().get(1).getName());
    }
}