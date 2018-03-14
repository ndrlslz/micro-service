package com.test.bff.controller;

import com.test.bff.model.Shop;
import com.test.bff.service.ShopServiceV1;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

public class ShopControllerTest {
    private ShopServiceV1 shopService;
    private ShopController shopController;

    @Before
    public void setUp() {
        shopService = Mockito.mock(ShopServiceV1.class);
        shopController = new ShopController(shopService);
    }

    @Test
    public void should_return_shops() throws Exception {
        Shop expected = new Shop();
        Mockito.when(shopService.retrieveShop(any())).thenReturn(expected);

        Shop shop = shopController.retrieveShop("1");

        assertEquals(expected, shop);
    }
}
