package com.test.order.service;

import com.test.order.entity.ShopEntity;
import com.test.order.model.Shop;
import com.test.order.service.ShopAssembler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Resource;

import static org.junit.Assert.*;

public class ShopAssemblerTest {
    private ShopAssembler assembler;

    @Before
    public void setUp() throws Exception {
        assembler = new ShopAssembler();
    }

    @Test
    public void should_to_resource() throws Exception {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setCode("code1");
        shopEntity.setName("name1");
        shopEntity.setId("1");

        Resource<Shop> shop = assembler.toResource(shopEntity);

        assertEquals("code1", shop.getContent().getCode());
        assertEquals("name1", shop.getContent().getName());
    }
}