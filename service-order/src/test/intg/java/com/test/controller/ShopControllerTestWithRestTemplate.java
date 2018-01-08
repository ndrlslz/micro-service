package com.test.controller;

import com.test.model.Shops;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerTestWithRestTemplate {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_with_rest_template() throws Exception {
        Shops shops = restTemplate.getForObject("/v1/shops", Shops.class);

        assertEquals(2, shops.getShops().size());
        assertEquals("code1", shops.getShops().get(0).getCode());
    }


}
